package com;

public class Record {

		private String reference;
		private String accountNumber;
		private String description;
		private double startBalance;
		private double mutation;
		private double endBalance;
		
		public String getReference() {
			return reference;
		}
		public void setReference(String reference) {
			this.reference = reference;
		}
		public String getAccountNumber() {
			return accountNumber;
		}
		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public double getStartBalance() {
			return startBalance;
		}
		public void setStartBalance(double startBalance) {
			this.startBalance = startBalance;
		}
		public double getMutation() {
			return mutation;
		}
		public void setMutation(double mutation) {
			this.mutation = mutation;
		}
		public double getEndBalance() {
			return endBalance;
		}
		public void setEndBalance(double endBalance) {
			this.endBalance = endBalance;
		}
		
		public String toString() {
			return "Reference: " + this.reference + "\n" + 
					"Account number: " + this.accountNumber+ "\n"+
					"Description: " + this.description+ "\n"+
					"Starting Balance: " + this.startBalance+ "\n"+
					"Mutation: " + this.mutation+ "\n"+
					"Ending Balance: " + this.endBalance+ "\n";
		}
}
