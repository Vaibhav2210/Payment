package com.vaibhav.ws.bank.ui.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhav.ws.bank.BankRepository;
import com.vaibhav.ws.bank.PeerBankRepository;
import com.vaibhav.ws.bank.UserRepository;
import com.vaibhav.ws.bank.exception.TransactionIdNotFoundException;
import com.vaibhav.ws.bank.io.entity.BankEntity;
import com.vaibhav.ws.bank.io.entity.UserBankEntity;
import com.vaibhav.ws.bank.io.entity.UserEntity;
import com.vaibhav.ws.bank.shared.utils.AesCryptUtil;
import com.vaibhav.ws.bank.ui.model.request.BankDetailsRquestModel;
import com.vaibhav.ws.bank.ui.model.request.RequestData;
import com.vaibhav.ws.bank.ui.model.response.BankRest;


@RestController
@RequestMapping("/bank")
public class BankController {
	
	@Autowired
	PeerBankRepository peerBankRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BankRepository bankRepository;
	
	String isCheckUVR="";
	int isRetry = 0;
	
	final static public String KEY = "1D2A907A05656A7E1A570B14C573D192";
	
	@GetMapping(path = "/")
	public String getAllUSers() {
		return "Get AllUser was called with page";
	}
	
	@PostMapping(path = "/view")
	public String getBankView() {
		isRetry = 0;
		System.out.println("isRetry : " + isRetry );
		return "isRetry set as 0";
	}
	
	
	@PostMapping("/bank")
	public List<BankEntity> bankEntity(@RequestBody BankEntity bankEntity) {
		
		
		return (List<BankEntity>) peerBankRepository.findAll();
		
	}
	
	@GetMapping("/users")
	public List<UserEntity> userEntity() {
		
		
		return (List<UserEntity>) userRepository.findAll();
		
	}
	
	@GetMapping("/newbank")
	public List<UserBankEntity> newbankEntity() {
		
		return (List<UserBankEntity>) bankRepository.findAll();
		
	}
	
	@GetMapping("/txnstatus")
	public UserEntity TransactionStatus(@RequestBody RequestData requestData) {
		
		UserEntity returnValue = userRepository.findByTransactionid(requestData.getTransactionid());
		if(requestData.getTransactionid().isEmpty() || returnValue == null) {throw new TransactionIdNotFoundException("Tranasction ID Not Found", requestData.getTransactionid());}
		return returnValue;
		
	}
	
	@PostMapping("/transaction")
	public UserEntity newbankEntity(@RequestBody RequestData requestData) {
		
		//RequestData requestData = new RequestData();
		String txn = requestData.getTransactionid();
		System.out.println(requestData.getTransactionid());
		System.out.println(requestData.getUVR());
		
		
		Optional<UserBankEntity> LS;
		LS = bankRepository.findById(txn);
		if(LS.isEmpty()) {throw new TransactionIdNotFoundException("!!Error!! Tranasction ID Not Found", txn);}
		
		System.out.println("LS VALUE__"+LS);
		
		isCheckUVR = LS.get().getUVR();
		System.out.println(LS.get().getUVR());
		System.out.println(isCheckUVR.equals(requestData.getUVR()));
		UserEntity userEntity  = userRepository.findByTransactionid(txn);
		
		while(!isCheckUVR.equals(requestData.getUVR()) || !LS.get().getTransactionid().equals(userEntity.getTransactionid())) {
			isRetry++;
			System.out.println(isRetry);
			break;
		}
		
		if(isCheckUVR.equals(requestData.getUVR())) {
			
			
			
			if(isRetry >= 3) {

				System.out.println("\n If -- If ");
				System.out.println("Transaction is already marked failed!!");
				return userEntity;
				//return userEntity;
			}else {
				System.out.println("\n If -- Else ");
				//System.out.println("Hello World");
				
				AesCryptUtil aesCryptUtil = new AesCryptUtil(KEY);
				System.out.println(aesCryptUtil.decrypt(isCheckUVR));
				
				
				//Save Status
				userEntity.setStatus("Transaction Successful");
				userEntity.setBank_verify(true);
				System.out.println(userEntity);
				
				userRepository.save(userEntity);
				
				//return bankRepository.findById(txn);
				
				return userEntity;			
				}
			
			//return userEntity;
			
		}else {
			System.out.println("\n Inside else");
			
			if(isRetry >= 3) {
				userEntity.setStatus("Transaction failed");
				//isRetry++;
				userEntity.setBank_verify(false);
				System.out.println(userEntity);
				
				userRepository.save(userEntity);
				System.out.println("\n Else -- If ");
				return userEntity;
			}else {
				System.out.println("\n Else -- Else ");
				return userEntity;
			}
			
			
			//return userEntity;
		}
		
		
		/*
		 * 
		 * if(!isCheckUVR.equals(requestData.getUVR()) && !LS.get().getTransactionid().equals(userEntity.getTransactionid())){
			System.out.println(bankRepository.findById(txn));
			//System.out.println(bankRepository.findById(requestData.getTransactionid().toString()));
			//return bankRepository.findById(null);
			//isRetry++;
			//Save Status
			/*userEntity.setStatus("Transaction failed");
			isRetry++;
			userEntity.setBank_verify(false);
			System.out.println(userEntity);
			
			userRepository.save(userEntity);
			System.out.println("\n Inside 2nd if");
			return userEntity;
		}else if(isRetry == 2){
			//isRetry++;
			
			userEntity.setStatus("Transaction failed");
			//isRetry++;
			userEntity.setBank_verify(false);
			System.out.println(userEntity);
			
			userRepository.save(userEntity);
			System.out.println("\n Inside 3rd");
			return userEntity;
		}
		 */
		
		
		//if(isCheckUVR.equals(requestData.getUVR()) || bankRepository.findById(txn).isEmpty()) {
			//System.out.println("Hello World");
			
		//}
		
	}
	
	@PostMapping("/new")
	public BankRest newpostUser(@RequestBody BankDetailsRquestModel newBankDetails) {
		
		BankRest retunValue = new BankRest();
		
		
		retunValue.setFirstname(newBankDetails.getFirstname());
		retunValue.setLastname(newBankDetails.getLastname());
		retunValue.setPhone(newBankDetails.getPhone());
		retunValue.setUVR("UVR123456789QWERYT");
	
		return retunValue;
		
	}
}