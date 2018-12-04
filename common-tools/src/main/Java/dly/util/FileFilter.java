package dly.util;

import java.io.File;
import java.io.FilenameFilter;

public class FileFilter implements FilenameFilter {
    private	String extList[] = null;

    public FileFilter()
    {

    }

    public FileFilter( String extName )
    {
        if( extName != null ){
            extList = StringUtil.split( extName );

            for( int ii=0; ii<extList.length; ii++ ){
                if( extList[ii].startsWith("*.") ){
                    extList[ii] = extList[ii].substring( 1 );
                }
                else if( extList[ii].startsWith(".") == false ){
                    extList[ii] = "." + extList[ii];
                }
            }
        }
    }

    public FileFilter( String exts[] )
    {
        extList = exts;
        for( int ii=0; ii<extList.length; ii++ ){
            if( extList[ii].startsWith("*.") ){
                extList[ii] = extList[ii].substring( 1 );
            }
            else if( extList[ii].startsWith(".") == false ){
                extList[ii] = "." + extList[ii];
            }
        }
    }

    /**
     @param dir
     @param name
     @return boolean
     */
    public boolean accept( File dir, String name )
    {
        if( extList == null || extList.length == 0 ){
            return true;
        }
        else{
            for( int ii=extList.length-1; ii>=0; ii-- ){
                if( name.endsWith(extList[ii]) ){
                    return true;
                }
            }

            return false;
        }
    }
}
