package fr.pizzeria.dao.exception;

public class SavePizza extends StockageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SavePizza(String msg) {
		super(msg);
	}
}
