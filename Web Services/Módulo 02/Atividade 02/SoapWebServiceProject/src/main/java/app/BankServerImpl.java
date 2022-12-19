package app;

import javax.jws.WebService;

@WebService(endpointInterface = "app.BankServer")
public class BankServerImpl implements BankServer{
	
	@Override
	public String getBankSlip(String barCode) {
		return "Boleto para o código" + barCode;
	}
	
	@Override
	public String createBankSlip(float value, String ClientCPF) {
		return "123.456.78910";
	}
	
	@Override
	public boolean verifyPayment(String barCode) {
		return true;
	}
	
	@Override
	public boolean cancelBankSlip(String barCode) {
		return false;
	}
	
}
