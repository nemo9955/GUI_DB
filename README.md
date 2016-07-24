# GUI_DB

Installation:

1. download Derby 10.12 (latest):
		https://db.apache.org/derby/releases/release-10.12.1.1.cgi 

2. extract the zip contents

3. run in cmd/terminal "*derby_folder*/bin/startNetworkServer" to start the Derby DataBase

4. once the derby server is up and running, either execute the startDBMaster42.jar or run Main.java


GUI:
RUN : runs the command in the TextArea and displays the output in the OutputTextArea below 
RUN TO CSV: runs the command, but exports the table to a CSV
Create the Table : creates the FACTURA table 
Show facturi : prints in the output area all the elements of the FACTURA table
Add to Factura : ads an row to the table FACTURA filled with random data
Delete FACTURA : delets the table FACTURA entirely
List DB : prints the schema of the DataBase
List Tables : prints all info regarding the tables
Import Data : Imports from a .CSV data and ads it to the table
