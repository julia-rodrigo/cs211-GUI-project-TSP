// 30/05/2021, 01,41

package TheActualThing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/*
1,38 Parsons Hall Maynooth ,4,53.37521,-6.6103
2,34 Silken Vale Maynooth ,6,53.37626,-6.59308
3,156 Glendale Leixlip ,18,53.37077,-6.48279
4,33 The Paddocks Oldtown Mill Celbridge ,8,53.3473,-6.55057
5,902 Lady Castle K Club Straffan ,11,53.31159,-6.60538
6,9 The Park Louisa Valley Leixlip ,3,53.36115,-6.48907
7,509 Riverforest Leixlip ,10,53.37402,-6.49363
8,16 Priory Chase St.Raphaels Manor Celbridge ,7,53.33886,-6.55468
9,13 Abbey Park Court Clane,13,53.2908,-6.67746
10,117 Royal Meadows Kilcock ,12,53.39459,-6.66995
11,7 Riverlawn Abbeyfarm Celbridge ,3,53.33239,-6.55163
12,10 Fair Green Court Kilccock ,7,53.39847,-6.66787
13,11 The Lodge Abbeylands Clane,12,53.29128,-6.67836
14,628 Riverforest Leixlip ,5,53.37416,-6.49731
15,12 Castlevillage Avenue Celbridge ,8,53.35298,-6.54921
16,116 Connaught Street Kilcock ,4,53.39839,-6.66767
17,44 Rinawade Avenue Leixlip ,20,53.36141,-6.51834
18,35 Beech Park Wood Beech Park Leixlip ,14,53.36287,-6.52468
19,96 Priory Lodge St. Raphael's Manor Celbridge ,2,53.33835,-6.53984
20,33 Leinster Wood Carton Demesne Maynooth ,7,53.39351,-6.5542
21,6 Glen Easton View Leixlip ,15,53.36883,-6.51468
22,40 Oaklawn West. Leixlip ,8,53.36833,-6.50589
23,169 Glendale Leixlip ,24,53.37043,-6.48193
24,14 The Rise Louisa Valley Leixlip ,15,53.36115,-6.48907
25,28 The Lawn Moyglare Abbey Maynooth ,7,53.38895,-6.60579
26,43 The Woodlands Castletown Celbridge ,12,53.34678,-6.53415
27,14 Rye River Crescent Dun Carraig Leixlip ,8,53.36518,-6.48913
28,32 The View St.Wolstan Abbey Celbridge ,10,53.33751,-6.53173
29,20 Habourview The Glenroyal Centre Maynooth ,9,53.37954,-6.58793
30,416A Ballyoulster Celbridge ,5,53.34133,-6.51856
31,10 Brookfield Avenue Maynooth ,8,53.36976,-6.59828
32,15 Willow Rise Primrose Gate Celbridge ,19,53.33591,-6.53566
33,3 Lyreen Park Maynooth ,26,53.38579,-6.58673
34,2 Beaufield Drive Maynooth ,10,53.37414,-6.60028
35,28 The Avenue Castletown Celbridge ,18,53.34514,-6.53615
36,4 Abbey Park Grove Clane ,14,53.29206,-6.67685
37,78 Crodaun Forest Park Celbridge ,15,53.35401,-6.54603
38,1 Kyldar House Manor Mills Maynooth ,29,53.38122,-6.59226
39,1002 Avondale Leixlip ,22,53.36869,-6.48314
40,18 College Green Maynooth ,5,53.37247,-6.60044

 */
public class TSP{

	public static JTable table = new JTable(); 				// put inputs into a Jtable
	
	public static double houses[][];						// store the distances/time
	public static String checked[][];						// store the path ways name
	public static double copyHouses[][];					// a copy of the houses[][] for BACH UP
	
	/*// TESTING FIELDS... eg of how the arrays look like:
	
	public static int [][]houses = {	{0, 58, 37, 57, 49, 34},
								        {58, 0, 44, 17, 53, 36},
								        {37, 44, 0, 45, 48, 50},
								        {57, 17, 45, 0, 6, 5},
								        {49, 53, 48, 6, 0, 24}, 
								        {34, 36, 50, 5, 24, 0} };
								        
	public static int [][]copyHouses = {	{0, 58, 37, 57, 49, 34},
										    {58, 0, 44, 17, 53, 36},
										    {37, 44, 0, 45, 48, 50},
										    {57, 17, 45, 0, 6, 5},
										    {49, 53, 48, 6, 0, 24}, 
										    {34, 36, 50, 5, 24, 0} };
	
	public static String [][] checked = {	{"[1, 1]", "[1, 2]", "[1, 3]", "[1, 4]", "[1, 5]", "[1, 6]"},
											{"[2, 1]", "[2, 2]", "[2, 3]", "[2, 4]", "[2, 5]", "[2, 6]"},
											{"[3, 1]", "[3, 2]", "[3, 3]", "[3, 4]", "[3, 5]", "[3, 6]"},
											{"[4, 1]", "[4, 2]", "[4, 3]", "[4, 4]", "[4, 5]", "[4, 6]"},
											{"[5, 1]", "[5, 2]", "[5, 3]", "[5, 4]", "[5, 5]", "[5, 6]"},
											{"[6, 1]", "[6, 2]", "[6, 3]", "[6, 4]", "[6, 5]", "[6, 6]"} };
	
	
	
	//*/
	
	public static double [] minutesTaken;	// store all the possible time taken for each route
	public static String [] allRoutes;		// store all the possible Route using the nearest neightbour algorythm
	public static int positionZero = 0;		// incriments everytime we finish calculating a path
	
    public static void main(String[] args){
        
        // create JFrame and JTable
        JFrame frame = new JFrame("Pizza Delivery");

        // create a table model and set a Column Identifiers to this model 
        String[] columns = {"Order No","Address","Minutes","GPSN", "GPSW"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        // set the model to the table
        table.setModel(model);
        
        // Change A JTable
        table.setBackground(Color.LIGHT_GRAY); // background colour of rows
        table.setForeground(Color.black); // font color 
        
        table.setRowHeight(20); // the thickness of each row
        
        
        // create JTextFields
        JTextArea insertText = new JTextArea(5, 10);
        JTextArea result = new JTextArea(5, 10);
        JTextArea time = new JTextArea(5, 10);
        
        JScrollPane scrollbar1 = new JScrollPane(insertText);
        //scrollbar1.setPreferredSize(new Dimension(500, 350));
        //scrollbar1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        // create JLabels 
        JLabel instructions = new JLabel ("insert <num, String, num, num, num>    eg.: <1, a, 1, 1, 1>");
        JLabel INSERT = new JLabel ("Insert/Paste here: ");
        JLabel RESULT = new JLabel ("Result: ");
        JLabel timeLabel = new JLabel ("Time (min): ");
        
        
        // create JButtons
        JButton btnAdd = new JButton("Add");    
        JButton route = new JButton("Find Route");    
        
        
        // position contents
        int moveLeft = -100;
        INSERT.setBounds(moveLeft + 150, 210, 500, 100);
        RESULT.setBounds(moveLeft + 150, 330, 500, 100);
        instructions.setBounds(moveLeft + 300, 180, 500, 100);
        insertText.setBounds(moveLeft + 300, 250, 500, 100);
        result.setBounds(moveLeft + 300, 370, 500, 150);
        btnAdd.setBounds(720, 250, 100, 25);
        route.setBounds(720, 370, 100, 60);
        
        int move = -120;
        int move2 = 35;
        time.setBounds(move + 180, 450 + move2, 100, 30);
        timeLabel.setBounds(move + 180, 135 + move2, 1000, 600);
        
        // border
        Border border = BorderFactory.createCompoundBorder(
        		result.getBorder(), 
                BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        result.setBorder(border);
        time.setBorder(border);
        //insertText.setBorder(border);
        
        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);
        
        
        JScrollPane pane2 = new JScrollPane(insertText);
        //pane2.setPreferredSize(new Dimension(500, 300));
        pane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        JScrollPane pane3 = new JScrollPane(result);
        //pane3.setPreferredSize(new Dimension(500, 300));
        pane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        frame.setLayout(null);
        frame.add(pane);
        frame.add(pane2);
        frame.add(pane3);
        
        // add JTextFields to the jframe
        frame.add(instructions);
        frame.add(INSERT);
        frame.add(RESULT);
        frame.add(insertText);
        frame.add(result);
        
        frame.add(time);
        frame.add(timeLabel);
    
        // add JButtons to the jframe
        frame.add(btnAdd);
        frame.add(route);
        
        // JScrollPane
        
        
        // create an array of String to set the row data
        String[] row = new String[5];
        
 // ----------------------------------------BUTTONS------------------------------------------------------------------
        
        
        // button add row ---------------------------------------------------------------
        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             
            	
            	String [] house = insertText.getText().split("\n"); /// if the text is a lot of lines
            	
            	/*
            	for(int i = 0; i < house.length; i++)
            	{
            		System.out.println("house: " + house[i]);
            	}*/
            	
            	//String valid = "";
            	for(int y = 0; y < house.length; y++)
            	{
            		int start = 0; // substring starting point
                	int pos = 0;	// substring end point
                	
            		if(house[y].equals("")) // if you didnt insert anything
                	{
                		System.out.println("no text inserted");
                	}
                	else
                	{
                		int count = 0;
                		
                		for(int i = 0; i < house[y].length(); i++)
                    	{
                			// if you find a ','...
                    		if(house[y].charAt(i) == ',')
                    		{
                    			// if there is a space after, take it out
                    			if(house[y].charAt(i + 1) == ' ')
                    			{
                    				row[pos] = house[y].substring(start, i);
                    				start = i + 2;
                    			}
                    			else
                    			{
                    				// no space, so record until next ','
                    				row[pos] = house[y].substring(start, i);
                    				start = i + 1;
                    			}
                    			
                    			pos++;	// row position updates
                    			count++; // count number of ','
                    		}
                    		if(count == 5) // if there are too much ',' then invalid
                    		{
                				count++;
                				pos = 0; // restart for the next code
                            	break;
                    		}
                    	}
                		
                		// this is the last row in the column that the code missed
                		row[pos] = house[y].substring(start);
                		
                		// if there are 4 commas, and specified rows are int, then add table
                		if(count == 4 & checkNum(row[0]) & checkNum(row[2]) & checkNum(row[3]) & checkNum(row[4]))
                		{
                        	model.addRow(row);
                		}
                		else
                		{
                			System.out.println("invalid text"); // otherwise invalid
                		}
                		
                		pos = 0; // update for the next column
                		count = 0; // update for the next ',' count
                    	
                	}
            	}
            	
            	
            	insertText.setText(""); // empty textarea
            	/*
                row[0] = textOrderNo.getText();
                row[1] = textAddress.getText();
                row[2] = textMinutes.getText();
                row[3] = textGPSN.getText();
                row[4] = textGPSW.getText();
                */
                
                // add row to the model
                
            }
        });
        
        // --------------------------------THIS IS IN POGGG LOL----------------------------------
        
        
        
        route.addActionListener(new ActionListener(){ // when you hit the 'route' button

            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	
            	// ---------------------------------------------------------------------------------------------------------------000000000000000
            	
            	houses = Ajacent_Matrix(table.getRowCount());  // CALCULATE adjacency matrix for distances/time between each houses
            	copyHouses = Ajacent_Matrix(table.getRowCount()); // also make a copy
            	checked = orders(table.getRowCount());			// labels for the travel
            	
            	allRoutes = new String [table.getRowCount()];	// store all the Routes accompnying time/distance taken
            	minutesTaken = new double [table.getRowCount()]; // store all the TOTAL distances calculated
            	
            	// -------- OPEN THESE AT THE TOP
            	
            	int i = 0; // starting from the first house
            	while(i < checked.length)
            	{
            		// calculate distance and routes
            		startFrom(i);
            		i++;
            		
            		// copy the ajacenty matrix again
            		copyBAK();
            	}
            	
            	
            	// Integer.parseInt(table.getModel().getValueAt(i, 2).toString())
            	
            	
            	double shortest = Integer.MAX_VALUE;  // find the shortest dISTANCE OUT OF THE 	WHOLE 	LOTt
            	int position = 0;
            	for(int a = 0; a < allRoutes.length; a++)
            	{
            		//System.out.println("Route: " + allRoutes[a]);
            		//System.out.println("TotalMinutes: " + (Math.round(minutesTaken[a]*0.1)/100) + "\n");
            		if(minutesTaken[a] < shortest)
            		{
            			
            			shortest = minutesTaken[a]; // store distance
            			position = a;				// store position
            		}
            		
            		//System.out.println("AngryMinutes: " + (minutesTaken[a] - 30 * allRoutes.length)+ "\n");
            	}
            		
            	///*
            	result.setText("Shortest Route:\n\n" + allRoutes[position]); // output result to textArea
            	time.setText("" + (Math.round(minutesTaken[position]*0.1)/100)); // output result to textArea
            	//*/
            	
            }	
        });
        
        
        // -----------------------------------------------------------------------------
        
        frame.setSize(900,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setVisible(true);
        
        
    }
    
 // ----------- since we emptied the adjacency matrix, we need to fill it up again for the next route ------------
    
    public static void copyBAK()
    {
    	for(int i = 0; i < checked.length; i++)
    	{
    		for(int j = 0; j < checked.length; j++)
        	{
    			houses[i][j] = copyHouses[i][j];
        	}
    	}
    }
    
 // ------------ Starting from this house, find the shortest route ---------------------------
    
    public static void startFrom(int i)
    {
    	String travelRoute = ""; // store houses visited

    	int length = houses.length; // length of house

    	//printMatrix();
    	
    	double smallDistance = Integer.MAX_VALUE;
    	
    	int jthis = i; // start at the first house
    	int updateI = i; // start at the first house
    	
    	int count = houses.length;
    	
    	double totalDistance = 0;
    	
    	//i = 2; // checkig
    	//System.out.println("\n" + "i: " +  i + "\n");
    	
    	// until we get to the last house
    	while (count != 1)
    	{
    		// go throught each houses
    		for(int j = 0; j < length; j++)
        	{
    			// is the distance/time takes to that house is smaller and not 0
    			if(houses[i][j] < smallDistance & houses[i][j] != 0) 
				{
    				updateI = i; // update i, to hold the smallest coordinates to far
    				jthis = j; // update j, to hold the smallest coordinates to far
    				smallDistance = houses [i][j]; // hold the house at that coordinate
				}
        	}
    		
    		i = updateI; // record that that house
    		
    		
    		// -------------------------------------------------------------------------------------------------------------
    		
    		
    		travelRoute = travelRoute + getHouse(checked[i][jthis])+ ", "; // write out the travel route
    		
    		//System.out.println("travelRoute: " +  travelRoute + "\n");
    		
    		
    		emptyRow(i); // cut out all paths from the previous house visited

    		i = jthis; // move to that house
    		count--; // check out that house from the list
    		
    		totalDistance = totalDistance + smallDistance; // update and add to the current distance so far
    		smallDistance = Integer.MAX_VALUE; // restart
    	}
    	
    	
    	travelRoute = travelRoute + lastHouse(checked[i][jthis]); // record the last house
    	
		//System.out.println("travelRoute: " +  travelRoute + "\n");
		//System.out.println("totalDistance: " + totalDistance + "\n");
    	
    	//result.setText(travelRoute);
    	
		minutesTaken[positionZero] = totalDistance; // Store the time taken for that travel
		allRoutes[positionZero] = travelRoute;		// Store the Route taken for that travel
		positionZero++;								// try a different route now
    	
    }
    
 // --------------- given String "[i, j]" , return the value at j ----------------------
    
    public static String lastHouse(String h)
    {
    	String how = "";
    	for(int i = 0; i < h.length(); i++)
    	{
    		if(h.charAt(i) == ',')
    		{
    			how = h.substring(i + 1, h.length() - 1);
    			//System.out.print("last how: " + how + "\t");
    		}
    	}
    	return how;
    	
    }
    
 // --------------- given String "[i, j]" , return the value at i ----------------------
    
    public static String getHouse(String h)
    {
    	int start = 1;
    	String how = "";
    	for(int i = 0; i < h.length(); i++)
    	{
    		if(h.charAt(i) == ',')
    		{
    			how = h.substring(start, i);
    			//System.out.print("how: " + how + "\t");
    		}
    	}
    	return how;
    	
    }
    
    // --------------- Print the two matrix (for checking) ----------------------
    
    public static void printMatrix()
    {
    	for(int i = 0; i < checked.length; i++)
    	{
    		for(int j = 0; j < checked.length; j++)
        	{
    			System.out.print(houses[i][j] + "\t");
        	}
    		System.out.print("\n");
    	}
    	for(int i = 0; i < checked.length; i++)
    	{
    		for(int j = 0; j < checked.length; j++)
        	{
    			System.out.print(checked[i][j] + "\t");
        	}
    		System.out.print("\n");
    	}
    }
    
    // --------------- take out all connections from Route i and its corresponding j ----------------------
    
    public static void emptyRow(int thisHouse)
    {
    	for(int i = 0; i < checked.length; i++)
    	{
    		for(int j = 0; j < checked.length; j++)
        	{
    			if(thisHouse == i || thisHouse == j)
    			{
    				houses[i][j] = 0;
    			}
    			//System.out.print(houses[i][j] + "\t");
        	}
    		//System.out.print("\n");
    	}
    }
    
    // -------------------------------- ajacent matrix calculation --------------------------------
    
    public static double[][] Ajacent_Matrix(int n)
    {
    	double [][] matrix = new double [n][n];
    	double time = 0;
    	//System.out.println("\n" + table.getModel().getValueAt(0, 3) + "\n");
    	
    	for(int i = 0; i < n; i++)
    	{
    		for(int j = 0; j < n; j++)
        	{
    			time = distance(Double.parseDouble((String)table.getValueAt(i, 3)), 
								Double.parseDouble((String)table.getValueAt(i, 4)), 
								Double.parseDouble((String)table.getValueAt(j, 3)), 
								Double.parseDouble((String)table.getValueAt(j, 4))
								//Integer.parseInt((String)table.getValueAt(j, 2)) 
								);
		
    			
    			matrix[i][j] = time;
    			
    			//System.out.print(matrix[i][j] + "\t");
    			/*
    			System.out.println(i + ", " + j + " =>: (" 
    								+ table.getValueAt(i, 3) + ", " + table.getValueAt(i, 4) + "), (" 
    								+ table.getValueAt(j, 3) + ", " + table.getValueAt(j, 4) + "): " 
    								+ matrix[i][j]);*/
        	}
    		//System.out.print("\n");
    	}
    	return matrix;
    }
    
    /// ------------------------------------- fill in the corresponding houses to distance matrix------------------------
    
    
    public static String[][] orders(int n)
    {
    	String [][] matrix = new String [n][n];
    	
    	//System.out.println("\n" + table.getModel().getValueAt(0, 3) + "\n");
    	for(int i = 0; i < n; i++)
    	{
    		for(int j = 0; j < n; j++)
        	{
    			// matrix will get the names of the paths, to the corresponding minutes
    			matrix[i][j] = "[" + (String)table.getValueAt(i, 0) + ", " + (String)table.getValueAt(j, 0) + "]";
    			//System.out.print(matrix[i][j] + "\t");
    			
    			/*
    			System.out.println(i + ", " + j + " =>: (" 
						+ table.getValueAt(i, 3) + ", " + table.getValueAt(i, 4) + "), (" 
						+ table.getValueAt(j, 3) + ", " + table.getValueAt(j, 4) + "): " 
						+ matrix[i][j]);
    			
    			// error
    			System.out.println("oder: " + table.getValueAt(i, 2));*/
    			
        	}
    		//System.out.print("\n");
    	}
    	return matrix;
    }
    
    
    // ---------- code for distance between two co-ordinates--------------------------------------
    
    public static double distance(Double lat1, Double lon1, Double lat2, Double lon2){
        final int R = 6371; // Radius of the earth
        
        Double lat = Math.toRadians(lat2-lat1);
        Double lon = Math.toRadians(lon2-lon1);
        
        Double a = 	Math.sin(lat / 2) * Math.sin(lat / 2) + 
			        Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * 
			        Math.sin(lon / 2) * Math.sin(lon / 2);
        
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        
        return (R * c)*1000; //distance in metres
    } 
    
    
    // ----------------------Check if the String input is a number-------------------------------------------
    
    public static boolean checkNum(String string)
    {
    	try {
            Double num = Double.parseDouble(string);
            //System.out.println(string);
        } catch (NumberFormatException e) {
        	//System.out.println("false " + string);
            return false;
        }
	    return true;
    }
}
