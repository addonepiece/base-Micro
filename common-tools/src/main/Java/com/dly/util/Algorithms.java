package com.dly.util;

import com.dly.exception.ProjectException;
import com.dly.exception.code.BasicErrCodes;

import java.io.File;
import java.io.InputStream;
import java.security.MessageDigest;

public class Algorithms
{
	private static final Algorithms instance = new Algorithms();
	public static final Algorithms getInstance()
	{
		return instance;
	}
	
	/* ************************** MD5 ***************************** */
	
	/**
	 * 取MD5
	 * @param fileName
	 * @return
	 */
	public final String getMD5Code( String fileName ) throws ProjectException
	{
		byte content[];
		InputStream	is = null;
		
		// 读取数据
		try{
			is = FileUtil.getResourceAsStream( fileName );
			content = FileUtil.readFile( is );
		}
		catch( ProjectException e ){
			e.addMessage( "读取文件[" + fileName + "]的内容时错误" );
			throw e;
		}
		finally{
			FileUtil.closeInputStream( is );
		}
		
		// 计算
		return getMessageDigest( content );
	}
	
	public final String getMD5Code( File file ) throws ProjectException
	{
		byte content[];
		InputStream	is = null;
		
		// 读取数据
		try{
			is = FileUtil.getResourceAsStream( file );
			content = FileUtil.readFile( is );
		}
		catch( ProjectException e ){
			e.addMessage( "读取文件[" + file.getAbsolutePath() + "]的内容时错误" );
			throw e;
		}
		finally{
			FileUtil.closeInputStream( is );
		}
		
		// 计算
		return getMessageDigest( content );
	}
	
	public final String getMessageDigest( byte data[] ) throws ProjectException
	{
		try{
			MessageDigest alg = MessageDigest.getInstance("MD5");
			alg.update( data );
			return StringUtil.byte2hex( alg.digest() );
		}
		catch (java.security.NoSuchAlgorithmException ex){
			throw BasicErrCodes.NO_MD5_ALGORITHM.exception(ex);
		}
		catch( Throwable e ){
			throw BasicErrCodes.GET_MD5_ERROR.exception( e );
		}
	}

	public final String getMessageDigest( byte data[], int offset, int length ) throws ProjectException
	{
		try{
			MessageDigest alg = MessageDigest.getInstance("MD5");
			alg.update( data, offset, length );
			return StringUtil.byte2hex( alg.digest() );
		}
		catch (java.security.NoSuchAlgorithmException ex){
			throw BasicErrCodes.NO_MD5_ALGORITHM.exception( ex );
		}
		catch( Throwable e ){
			throw BasicErrCodes.GET_MD5_ERROR.exception( e );
		}
	}

	public final String getMessageDigest( String data ) throws ProjectException
	{
		try{
			MessageDigest alg = MessageDigest.getInstance("MD5");
			alg.update( data.getBytes(Constants.CHARSET_NAME) );
			return StringUtil.byte2hex( alg.digest() );
		}
		catch (java.security.NoSuchAlgorithmException ex){
			throw BasicErrCodes.NO_MD5_ALGORITHM.exception( ex );
		}
		catch( Throwable e ){
			throw BasicErrCodes.GET_MD5_ERROR.exception( e );
		}
	}

	

	public final String getMD5Code( String[] fileList ) throws ProjectException
	{
		if( fileList == null || fileList.length == 0 ){
			return "";
		}
		
		byte result[] = null;
		for( String fileName : fileList ){
			byte content[];
			InputStream	is = null;
			
			// 读取数据
			try{
				is = FileUtil.getResourceAsStream( fileName );
				content = FileUtil.readFile( is );
			}
			catch( ProjectException e ){
				e.addMessage( "读取文件[" + fileName + "]的内容时错误" );
				throw e;
			}
			finally{
				FileUtil.closeInputStream( is );
			}
			
			if( result == null ){
				result = getMessageByte( content );
			}
			else{
				byte r2[] = getMessageByte( content );
				int size = r2.length;
				if( size > result.length ){
					size = result.length;
				}
				
				for( int x=0; x<size; x++ ){
					result[x] += r2[x];
				}
			}
		}
		
		// 计算
		return StringUtil.byte2hex( result );
	}

	public final byte[] getMessageByte( byte data[] ) throws ProjectException
	{
		try{
			MessageDigest alg = MessageDigest.getInstance("MD5");
			alg.update( data );
			return alg.digest();
		}
		catch (java.security.NoSuchAlgorithmException ex){
			throw BasicErrCodes.NO_MD5_ALGORITHM.exception(ex);
		}
		catch( Throwable e ){
			throw BasicErrCodes.GET_MD5_ERROR.exception( e );
		}
	}

	public final byte[] getMessageByte( String data ) throws ProjectException
	{
		try{
			MessageDigest alg = MessageDigest.getInstance("MD5");
			alg.update( data.getBytes(Constants.CHARSET_NAME) );
			return alg.digest();
		}
		catch (java.security.NoSuchAlgorithmException ex){
			throw BasicErrCodes.NO_MD5_ALGORITHM.exception( ex );
		}
		catch( Throwable e ){
			throw BasicErrCodes.GET_MD5_ERROR.exception( e );
		}
	}
	
	/* ************************** BASE64 ************************** */
	
	public final String encodeBase64( byte[] source )
	{
		return Base64.encodeBytes(source);
	}
	
	public final String encodeBase64( byte[] source, int off, int len )
	{
		return Base64.encodeBytes(source, off, len);
	}
	
	public final String encodeBase64File( String fileName )
	{
		return Base64.encodeFromFile(fileName);
	}
	
	public final byte[] decodeBase64( String s )
	{
		return Base64.decode(s);
	}
	
	public final byte[] decodeBase64File( String fileName )
	{
		return Base64.decodeFromFile(fileName);
	}
	

	public final String getMessageDigest64( String data ) throws ProjectException
	{
		try{
			MessageDigest alg = MessageDigest.getInstance("MD5");
			alg.update( data.getBytes(Constants.CHARSET_NAME) );
			return encodeBase64( alg.digest() );
		}
		catch (java.security.NoSuchAlgorithmException ex){
			throw BasicErrCodes.NO_MD5_ALGORITHM.exception( ex );
		}
		catch( Throwable e ){
			throw BasicErrCodes.GET_MD5_ERROR.exception( e );
		}
	}
}
