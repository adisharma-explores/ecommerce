package com.shopping.ecommerce.Exceptions;


public class InvalidPasswordException extends Exception{
	InvalidPasswordException(String msg)
	{
		super(msg);
	}
}