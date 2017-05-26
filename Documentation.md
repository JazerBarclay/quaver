# Technical Reference


## File Structure
### Libraries
These are folders that contain all notebooks and their notes. This is to be named as the library name and to be appended with '.qvLibrary' for searching and for the application.
    
Each library will soon contain a meta file for managing all child notebooks and notes. This will be used for holding carat positioning, version value and for history backups in the even of a failure in the program (This is subject to change with updates until v2.0)

### Notebook
A notebook is a store for notes. This contains a meta file for holding the uuid of the notebook and the name of the notebook. This is used to refer to the notebook in the application and the uuid for the list.
#### Meta
    Name: (String)
    UUID: (String)

#### Contains array note folders
    Folder names are to be ended with 

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
