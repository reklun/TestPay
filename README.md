# TestPay

Run:
1. Drop the CSV files under resources/input.
2. Run the Worker.class, which is a main method.
3. Get the output from resources/output.



Assumptions:
1. Everyone touch on when they get on board.
2. There is a maximum time to force a touch off if a touch off is not happening. 
   Default to 4 hours defined in the Constant class.
3. Each Bus has a unique ID.
4. Company ID are unique.
5. PAN are unique.
6. The matching conditions are same PAN, same BusID, same CompanyID and tapTime are bounded within 4 hours.
7. Files are complete with data in right format and values, especially for ENUM tapType and StopID.
8. Assume the records come in a correct time sequence. Not sure if this is a valid assumption. If the records dont come 
   in the right order, the pairing logic needs to be enhanced.
9. All information are included in the CSV offered. For any records missing OFF, they are treated as CANCELLED trips.

