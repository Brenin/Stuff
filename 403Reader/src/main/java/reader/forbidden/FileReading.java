package reader.forbidden;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileReading {
	public static void main(String[] args) {

		CharsetDecoder dec = StandardCharsets.UTF_8.newDecoder().onMalformedInput(CodingErrorAction.REPLACE);
		
		String accessLog = "filePath";
		Path readPath = Paths.get(accessLog);
		
		String testFile = "testFile"; //Will create the file if it doesn't exist
		Path writePath = Paths.get(testFile);
		
		List<String> list = new ArrayList<>();
		List<Integer> rows = new ArrayList<>();
		
		try(Reader reader = Channels.newReader(FileChannel.open(readPath), dec, -1);
			BufferedReader br = new BufferedReader(reader);
			BufferedWriter writer = Files.newBufferedWriter(writePath)){
				
				list = br.lines()
							.filter(line ->  line.startsWith("https") 
		            		  			  || line.startsWith("HTTP")
		            		  			  || line.startsWith("Date"))
							.collect(Collectors.toList());
				
				int i = 0;
				for(String str : list){
					String value = str.replaceAll("[^0-9]","");
					String string = value.substring(2);
					if(string.equals("403")){
						rows.add(i);
					}
					i++;
				}
				
				for(Integer row : rows){
					writer.write(list.get(row - 1) + "\n");
					writer.write(list.get(row) + "\n"); // Not needed but nice for confirmation
					writer.write(list.get(row + 1) + "\n");
					writer.write("\n");
				}
				
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("List size: " + list.size());
		System.out.println("Rows size: " + rows.size());
	}
}
