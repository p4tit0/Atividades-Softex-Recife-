package app;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


@WebService
@SOAPBinding(style = Style.RPC)
public interface BankServer {
	@WebMethod String getBankSlip(String barCode);
	@WebMethod String createBankSlip(float value, String ClientCPF);
	@WebMethod boolean verifyPayment(String barCode);
	@WebMethod boolean cancelBankSlip(String barCode);
}
