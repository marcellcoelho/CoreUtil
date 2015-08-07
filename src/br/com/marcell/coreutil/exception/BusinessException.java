package br.com.marcell.coreutil.exception;


public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;

	public BusinessException() {
		super();
	}
	
	public BusinessException(String mensagem){
		this.message = mensagem;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
