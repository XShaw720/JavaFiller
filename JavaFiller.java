import java.io.*;
import java.util.*;

public class JavaFiller{
	
	private static void write(String line, FileWriter w)throws Exception{	//allows escape characters, only ones I implemented are /n/r/t but any others can be added by adding a case
		for(int i=0; i<line.length(); i++){
			switch(line.charAt(i)){
				case '\\':
					switch(line.charAt(i+1)){
						case 'n':
							w.write("\n");
							i++;
							break;
						case 'r':
							w.write("\r");
							i++;
							break;
						case 't':
							w.write("\t");
							i++;
							break;
					}
					break;
				default:
					w.write(line.charAt(i));
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		
		String before="", after="", between="", line, in="input.txt", out="output.txt";
		boolean found=true;		
		
		switch(args.length){		//allows commandline args for i/o files, defaults "input.txt" and "output.txt"
			case 2:
				out=args[1];		//if two files are given the first is used for input and the second for output
			case 1:
				in=args[0];			//if only one file is given it used as input
		}	

		Scanner s = new Scanner(new File(in));
		FileWriter w=new FileWriter(new File(out));		
		
		while(s.hasNextLine()){
			line=s.nextLine();
			if(line.matches("before .*"))			//what between "" will be printed before each line
				before=line.substring(line.indexOf(" ")+1);
			
			else if(line.matches("after .*"))		//whats between "" will be printed after each line
				after=line.substring(line.indexOf(" ")+1);
			
			else if(line.matches("between .*"))	//whats between "" will be printed between each line
				between=line.substring(line.indexOf(" ")+1);
			
			else if(line.matches("print .*"))
				write(line.substring(line.indexOf(" ")+1), w);

			else if(line!=""){		//does't print empty lines
				write(before, w);
				write(line, w);
				write(after, w);
				if(s.hasNextLine())
					write(between, w);		//only prints the between if theres another line
			}
		}	
		s.close();
		w.close();
	}
}
			