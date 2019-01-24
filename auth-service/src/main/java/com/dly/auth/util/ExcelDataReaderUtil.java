package com.dly.auth.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dly.exception.ProjectException;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFReader.SheetIterator;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public abstract class ExcelDataReaderUtil {

    enum xssfDataType {
        BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER,
    }

    public static final int ERROR = 1;
    public static final int BOOLEAN = 1;
    public static final int NUMBER = 2;
    public static final int STRING = 3;
    public static final int DATE = 4;
    public static final String DATE_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";


    private InputStream sheet;
    private XMLReader parser;
    private InputSource sheetSource;
    private int index = 0;
    private OPCPackage pkg;
    private String preIndex = null;
    private String afterIndex = null;

    /**
     * 工作表索引
     */
    private int sheetIndex = -1;


    /**
     * 工作表当前读取的sheet名称
     */
    private String sheetName = "data";

    /**
     * 读大数据量Excel
     */

    public ExcelDataReaderUtil(String filename) throws IOException, OpenXML4JException, SAXException {
        pkg = OPCPackage.open(filename);
        init(pkg);
    }

    /**
     * 读大数据量Excel
     */
    public ExcelDataReaderUtil(File file) throws IOException, OpenXML4JException, SAXException {
        pkg = OPCPackage.open(file);
        init(pkg);
    }

    /**
     * 读大数据量Excel
     *
     * @param in Excel文件输入流
     * @return
     * @throws IOException
     * @throws OpenXML4JException
     * @throws SAXException
     */
    public ExcelDataReaderUtil(InputStream in) throws IOException, OpenXML4JException, SAXException {
        pkg = OPCPackage.open(in);
        init(pkg);
    }

    /**
     * 初始化 将Excel转换为XML
     *
     * @param pkg
     * @throws IOException
     * @throws OpenXML4JException
     * @throws SAXException
     */
    private void init(OPCPackage pkg) throws IOException, OpenXML4JException, SAXException {

        XSSFReader xssfReader = new XSSFReader(pkg);
        SharedStringsTable sharedStringsTable = xssfReader.getSharedStringsTable();
        StylesTable stylesTable = xssfReader.getStylesTable();
        SheetIterator sheets = (SheetIterator) xssfReader.getSheetsData();
        while (sheets.hasNext()) {
            sheetIndex++;
            index = 0;
            InputStream sheet = sheets.next();
            sheetName = sheets.getSheetName();
            ErrorHandler errorHandler = new CustomErrorHandler();
            parser = fetchSheetParser(sharedStringsTable, stylesTable);
            parser.setErrorHandler(errorHandler);
            sheetSource = new InputSource(sheet);
            try {
                parser.parse(sheetSource);
            } catch (SAXException e) {
                throw e;
            } finally {
                sheet.close();

            }
        }
        pkg.close();
    }

    /**
     * 执行解析操作
     *
     * @return 读取的Excel行数
     */
    public int parse() {
        try {
            parser.parse(sheetSource);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            if (sheet != null) {
                try {
                    sheet.close();
                    pkg.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return index;
    }

    private XMLReader fetchSheetParser(SharedStringsTable sharedStringsTable, StylesTable stylesTable) throws SAXException {
        XMLReader parser =
                XMLReaderFactory.createXMLReader(
                        "org.apache.xerces.parsers.SAXParser"
                );
        ContentHandler handler = new SheetHandler(sharedStringsTable, stylesTable);
        parser.setContentHandler(handler);
        return parser;
    }

    /**
     * SAX解析的处理类
     * 每解析一行数据后通过outputRow(String[] datas, int[] rowTypes, int rowIndex)方法进行输出
     *
     * @author zpin
     */
    private class SheetHandler extends DefaultHandler {
        private SharedStringsTable sharedStringsTable; // 存放映射字符串
        private StylesTable stylesTable;// 存放单元格样式
        private String readValue;// 存放读取值
        private xssfDataType dataType;// 单元格类型
        private List<String> rowDatas = new ArrayList<String>(1000);// 存放一行中的所有数据
        private short formatIndex;

        private SheetHandler(SharedStringsTable sst, StylesTable stylesTable) {
            this.sharedStringsTable = sst;
            this.stylesTable = stylesTable;
        }

        public void startElement(String uri, String localName, String name,
                                 Attributes attributes) throws SAXException {
            if (name.equals("c")) {// c > 单元格
                String cellType = attributes.getValue("t");
                String cellStyle = attributes.getValue("s");
                if (preIndex == null) {
                    preIndex = attributes.getValue("r");
                } else {
                    preIndex = afterIndex;
                }
                afterIndex = attributes.getValue("r");
                if (code2int(afterIndex.substring(0, 1)) - rowDatas.size() > 0) {
                    rowDatas.add(null);
                }
                this.dataType = xssfDataType.NUMBER;
                if ("b".equals(cellType)) {
                    this.dataType = xssfDataType.BOOL;
                } else if ("e".equals(cellType)) {
                    this.dataType = xssfDataType.ERROR;
                } else if ("inlineStr".equals(cellType)) {
                    this.dataType = xssfDataType.INLINESTR;
                } else if ("s".equals(cellType)) {
                    this.dataType = xssfDataType.SSTINDEX;
                } else if ("str".equals(cellType)) {
                    this.dataType = xssfDataType.FORMULA;
                } else if (cellStyle != null) {
                    int styleIndex = Integer.parseInt(cellStyle);
                    XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
                    this.formatIndex = style.getDataFormat();
                }
            }
            readValue = "";
        }

        public void endElement(String uri, String localName, String name)
                throws SAXException {
            if (name.equals("v")) { // 单元格的值
                switch (this.dataType) {
                    case BOOL: {
                        char first = readValue.charAt(0);
                        rowDatas.add(first == '0' ? "FALSE" : "TRUE");

                        break;
                    }
                    case ERROR: {
                        rowDatas.add("ERROR:" + readValue.toString());

                        break;
                    }
                    case INLINESTR: {
                        rowDatas.add(new XSSFRichTextString(readValue).toString());

                        break;
                    }
                    case SSTINDEX: {

                        int idx = Integer.parseInt(readValue);
//                            //如果有空单元格 补一个空数据
//                    		if(code2int(afterIndex.substring(0,1))-code2int(preIndex.substring(0,1))>1)
//                        	{
//                        		rowDatas.add(null);
//                        	}
//
//                        if(new XSSFRichTextString(sharedStringsTable.getEntryAt(idx)).toString()==""||new XSSFRichTextString(sharedStringsTable.getEntryAt(idx)).toString().length()<1)
//                        {
//                        	rowDatas.add(null);
//                        	  break;
//                        }
                        rowDatas.add(new XSSFRichTextString(sharedStringsTable.getEntryAt(idx)).toString());

                        break;
                    }
                    case FORMULA: {
                        rowDatas.add(readValue);

                        break;
                    }
                    case NUMBER: {
                        // 判断是否是日期格式
                        if (HSSFDateUtil.isADateFormat(formatIndex, readValue)) {
                            Double d = Double.parseDouble(readValue);
                            Date date = HSSFDateUtil.getJavaDate(d);
                            rowDatas.add(DateFormatUtils.format(date, DATE_FORMAT_STR));

                        } else {
                            rowDatas.add(readValue);
                        }
                        break;
                    }
                }
            } else if (name.equals("t")) { // 单元格的值
                switch (this.dataType) {
                    case BOOL: {
                        char first = readValue.charAt(0);
                        rowDatas.add(first == '0' ? "FALSE" : "TRUE");
                        break;
                    }
                    case ERROR: {
                        rowDatas.add("ERROR:" + readValue.toString());
                        break;
                    }
                    case INLINESTR: {
//                    	//判断空单元格
//                    	if(code2int(afterIndex.substring(0,1))-code2int(preIndex.substring(0,1))>1)
//                    	{
//                    		rowDatas.add(null);
//                    		break;
//                    	}
                        if (StringEscapeUtils.unescapeHtml(readValue).trim() == "" || StringEscapeUtils.unescapeHtml(readValue).trim().length() < 1) {
                            break;
                        }
                        rowDatas.add(StringEscapeUtils.unescapeHtml(readValue).trim());
                        break;
                    }
                    case SSTINDEX: {
                        int idx = Integer.parseInt(readValue);
                        rowDatas.add(new XSSFRichTextString(sharedStringsTable.getEntryAt(idx)).toString());
                        break;
                    }
                    case FORMULA: {
                        rowDatas.add(readValue);
                        break;
                    }
                    case NUMBER: {
                        // 判断是否是日期格式
                        if (HSSFDateUtil.isADateFormat(formatIndex, readValue)) {
                            Double d = Double.parseDouble(readValue);
                            Date date = HSSFDateUtil.getJavaDate(d);
                            rowDatas.add(DateFormatUtils.format(date, DATE_FORMAT_STR));
                        } else {
                            rowDatas.add(readValue);
                        }
                        break;
                    }
                }
            }

            // 当解析的一行的末尾时，输出数组中的数据
            else if (name.equals("row")) {
                try {
                    outputRow(rowDatas, index++, sheetIndex, sheetName);
                } catch (Exception e) {
                    throw new SAXException(e);

                }

                rowDatas.clear();
            }
        }

        public void characters(char[] ch, int start, int length)
                throws SAXException {
            readValue += new String(ch, start, length);
        }
    }

    /**
     * 输出每一行的数据
     */
    protected abstract void outputRow(List<String> datas, int rowIndex, int sheetIndex, String sheetName) throws ProjectException;

    private int getColumn(Attributes attrubuts) {
        String name = attrubuts.getValue("r");
        int column = -1;
        for (int i = 0; i < name.length(); ++i) {
            if (Character.isDigit(name.charAt(i))) {
                break;
            }
            int c = name.charAt(i);
            column = (column + 1) * 26 + c - 'A';
        }
        return column;
    }

    private int getColsNum(Attributes attrubuts) {
        String spans = attrubuts.getValue("spans");
        String cols = spans.substring(spans.indexOf(":") + 1);
        return Integer.parseInt(cols);
    }

    /*
     * 将字母转换成数字
     */
    private int code2int(String code) {
        int num = 0;
        char[] charArray = code.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            Integer count = charArray[i] - 64;
            num = num * 26 + count;
        }
        num = num - 1;
        return num;
    }

}
