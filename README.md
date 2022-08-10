# TestPay
#-----------------------------------------------

Run:

1. Drop the CSV files under `src/main/resources/input`. 
   There's no validation on file names and data inside, assuming all to be right.
   You can drop multiple files in the same directory.
   
2. Run the `Worker.class`, which is a main method.

3. Get the output from `src/main/resources/output`.
   Default file name as `trips.csv`

#-----------------------------------------------

Test:

`gradle build` will complete the tests

Or Run Unit test per class. The most important test class is `WorkFlowProcessorTest`


#-----------------------------------------------

Log file:

Config file under `src/main/resources/log4j2.properties`
Log file can be found under `log/app.log`

#-----------------------------------------------

Assumptions:

1. Everyone touch on when they get on board.
2. There is a maximum time to force a touch off if a touch off is not happening. 
   Default to 4 hours defined in the Constant class.
3. Each Bus has a unique ID. The input format is always "Bus" followed by a number.
4. Company ID are unique.
5. PAN are unique.
6. The matching conditions are same PAN, same BusID, same CompanyID and tapTime are bounded within 4 hours.
7. Files are complete with data in right format and values, especially for ENUM tapType and StopID.
8. Assume the records come in a correct time sequence. Not sure if this is a valid assumption. If the records dont come 
   in the right order, the pairing logic needs to be enhanced.
9. All information are included in the CSV offered. For any records missing OFF, they are treated as CANCELLED trips.
10. For incomplete trip, ending time and duration cannot be determined

#-----------------------------------------------

