# Technical Reference


## File Structure
### Libraries
These are folders that contain all notebooks and their notes. This is to be named as the library name and to be appended with '.qvLibrary' for searching and for the application.
    
Each library will soon contain a meta file for managing all child notebooks and notes. This will be used for holding carat positioning, version value and for history backups in the even of a failure in the program (This is subject to change with updates until v2.0)

### Notebook
A notebook is a store for notes. The folder should be named with the notebook name and be prefixed with '.qvNotebook'. This contains a meta file for holding the UUID of the notebook and the name of the notebook. This is used to refer to the notebook in the application and the UUID for the list.

#### Folders
Folder names are to be named with their UUID values and to be prefixed with '.qvNote'

#### Meta
    Name: (String)
    UUID: (String)

### Note
This is the core of the note taking functionality. The meta file contains all details on modifications, generation and referencing tags. The content file holds all data in cells. These allow for the use of different formats such as markdown, mathjax, plain text and potentially more. The title used in the content is the display title in the preview document. The meta title is for the listing in the menu.
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
