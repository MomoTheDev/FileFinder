# FileFinder
A fast file finder for Windows, developed in Java

# Installation
1. Install the latest FileFinder.jar file from here: https://github.com/MomoTheSiM/FileFinder/releases
2. Open a command prompt window in the same folder as the file
3. Type this command: `Java -jar ./FileFinder.jar KEYWORD PATH/TO/STARTING/POINT .HIDE_ERRORS`
4. Replace the `KEYWORD` in the command, with your own keyword
5. Replace the `PATH/TO/STARTING/POINT` in the command, with your own starting path
6. Run the command and wait for it to finish!

# Flags
1. `.HIDE_ERRORS` This flag hides all the errors while searchings, so they won't be printed out

# Construction
The `SearchOperation`-Class implements the `FileOperation`-Interface.
I'm thinking of adding new file operations to the program, you can use and modify this interface or the program the way you want
