# Technical Reference


		## File Structure
		### Notebook
		#### Meta
		  Name: (String)
		  UUID: (String)
		
		#### Contains array note folders
		  Folder names
		
		### Note
		
		#### Content
		  Title: (String)
		  Cells: {
		    Data: (String)
		    Type: (String) <- Enum
		    Language: (String) <- To link to enum
		  }
		  
		#### Meta
		  Title: (String)
		  UUID: (String)
		  Created_at: (Long)
		  Updated_at: (Long)
		  Tags: {
		  	(String)...
		  }
