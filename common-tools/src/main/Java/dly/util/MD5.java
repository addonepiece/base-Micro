package dly.util;

import java.io.File;
import java.io.InputStream;

import dly.exception.ProjectException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MD5
{

	private static final Algorithms alg = Algorithms.getInstance();
	
	/**
	 * 计算文件的MD5编码
	 * @param fileName
	 * @return
	 */
	public static String getMD5Code( String fileName )
	{
		try{
			return alg.getMD5Code(fileName);
		}
		catch( ProjectException e ){
			log.error( "读取文件[" + fileName + "]的内容时错误" );
			return null;
		}
	}

	public static String getMD5Code( File file )
	{
		try{
			return alg.getMD5Code(file);
		}
		catch( ProjectException e ){
			log.error( "读取文件[" + file.getAbsolutePath() + "]的内容时错误" );
			return null;
		}
	}

	public static String getMD5Code( String[] fileList )
	{
		try{
			return alg.getMD5Code(fileList);
		}
		catch( ProjectException e ){
			log.error( "读取文件的内容时错误" );
			return null;
		}
	}

	/**
	 * 计算签名信息
	 * @param data
	 * @return
	 */
	public static String getMessageDigest( byte data[] )
	{
		try{
			return alg.getMessageDigest(data);
		}
		catch( ProjectException e ){
			log.error( "计算摘要时错误", e );
			return null;
		}
	}

	public static String getMessageDigest( byte data[], int offset, int length )
	{
		try{
			return alg.getMessageDigest(data, offset, length);
		}
		catch( ProjectException e ){
			log.error( "计算摘要时错误", e );
			return null;
		}
	}
	
	/**
	 * 计算签名信息
	 * @param data
	 * @return
	 */
	public static String getMessageDigest( String data )
	{
		try{
			return alg.getMessageDigest(data);
		}
		catch( ProjectException e ){
			log.error( "计算摘要时错误", e );
			return null;
		}
	}

	public static byte[] getMessageByte( String data )
	{
		try{
			return alg.getMessageByte(data);
		}
		catch( ProjectException e ){
			log.error( "计算摘要时错误", e );
			return null;
		}
	}
	
	/**
	 * 同步文件
	 * @param src
	 * @param dest
	 */
	public static void syncFile( String src, String dest )
	{
		// 文件内容
		byte content[];
		InputStream is = null;
		
		try{
			// 读取源文件
			is = FileUtil.getResourceAsStream( src );
			content = FileUtil.readFile( is );
		}
		catch( Throwable e ){
			log.error( "读取文件[" + src + "]内容时错误", e );
			return;
		}
		finally{
			FileUtil.closeInputStream( is );
		}
		
		// 取摘要
		String d1 = MD5.getMessageDigest( content );
		if( d1 == null ){
			return;
		}
		
		// 取目标文件的摘要
		String d2 = MD5.getMD5Code( dest );
		
		// 判断文件是否相同
		if( d2 != null && d2.compareTo(d1) == 0 ){
			return;
		}
		
		// 生成文件
		try{
			FileUtil.saveFile( dest, content );
			log.info( "生成文件[" + src + "] ==> [" + dest + "]" );
		}
		catch( Exception e ){
			log.error( "生成文件[" + dest + "]时错误", e );
		}
	}
}

