/**
 * 
 */
package br.com.adapt.exception;

/**
 * @author mayra
 *
 */
public class InvalidUserException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidUserException(String msg) {
		super(msg);
	}

}
