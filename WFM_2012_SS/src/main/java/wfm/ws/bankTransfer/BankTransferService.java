package wfm.ws.bankTransfer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import wfm.bean.BankAccount;

@WebService
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED)
public interface BankTransferService 
{
	/**
	 * <p>Validates the provided account data of a specified bank account and returns
	 * an object which represents the bank account. If the provided data is not valid
	 * a {@link BankTransferValidationFault} will be thrown</p>
	 * @param bankName Name of the bank, f.e. Bank Austria
	 * @param bankCode Bank code number of the bank, f.e. 12000
	 * @param bankAccountNumber Account number of a person at a certain bank
	 * @param bankAccountHoldersName Name of the person owning the account
	 * @return Returns a {@link BankAccount} object if the provided data can be validated
	 * @throws BankTransferValidationFault Will be thrown if the data could not be validated.
	 * 										This might be caused due to wrong data input.
	 */
	@WebMethod(operationName="validateBankAccount", action="urn:ValidateBankAccount")
	public @WebResult(name="bankAccount") BankAccount validateBankAccount(
			@WebParam(name="bankName") String bankName, 
			@WebParam(name="bankCode") String bankCode, 
			@WebParam(name="accountNumber") String bankAccountNumber, 
			@WebParam(name="holdersName") String bankAccountHoldersName) 
					throws BankTransferValidationFault;
	
	/**
	 * <p>Executes a payment for a certain account if the current balance of the account is
	 * bigger than the amount to pay.</p>
	 * @param accountNo Account number of a person at a certain bank
	 * @param bankCode Bank code number of the bank, f.e. 12000
	 * @param amount The amount of money to withdraw from the account
	 * @return Returns true if the payment succeeded, false if the amount to pay is bigger than
	 *         the current accounts balance
	 * @throws AccountNotFoundFault Will be thrown if the account could not be found
	 */
	@WebMethod(operationName="doPayment", action="urn:BankTransferPayment")
	public @WebResult(name="success") boolean doPayment(
			@WebParam(name="accountNo") String accountNo,
			@WebParam(name="bankCode") String bankCode,
			@WebParam(name="amount") Double amount) 
					throws AccountNotFoundFault;
}
