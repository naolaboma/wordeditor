# WordEditor

WordEditor is a Java-based text editor application designed to provide rich text editing capabilities. It includes features such as text formatting, paragraph alignment, clipboard operations, file management, and color customization. The application is built using Swing for the graphical user interface and supports RTF (Rich Text Format) editing.

## Features

### Text Formatting
- **Bold**, **Italic**, and **Underline** text styles.
- Font family and font size customization.

### Paragraph Formatting
- Align text to **left**, **center**, or **right**.
- **Justify** text.
- Adjust **line spacing** and **paragraph spacing**.
- Indent and outdent paragraphs.

### Clipboard Operations
- **Cut**, **Copy**, and **Paste** text.

### File Management
- Create a **new file**.
- **Open** existing files.
- **Save** and **Save As** files in `.wrd` format.
- **Close** and **Exit** the application.

### Color Customization
- Change **background color** and **foreground color** of the text.

### Find and Replace
- Search for text and replace it with new content.

### Undo and Redo
- Undo and redo text changes using an **UndoManager**.

### Word Counter
- Count the number of words in the document.

### Printing
- Print the document using the system's printer.

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/naolaboma/wordeditor.git
   ```

2. Open the project in your IDE (e.g., IntelliJ IDEA or Eclipse).

3. Ensure you have Java 8 or higher installed.

4. Run the `Application.java` or `App.java` file to start the editor.

## Usage

1. Use the menu bar or toolbar buttons to access features like file operations, text formatting, and paragraph alignment.
2. Customize text colors using the **Background Color** and **Foreground Color** buttons.
3. Use the **Find and Replace** feature to search and replace text in the document.
4. Save your work in `.wrd` format or print the document directly.

## Project Structure

```
src/
├── wordEditor/
│   ├── AddToolTips.java
│   ├── App.java
│   ├── Application.java
│   ├── CssClass.java
│   ├── CustomColor.java
│   ├── CustomImage.java
│   ├── CustomStyle.java
│   ├── EditLogic.java
│   ├── FileLogic.java
│   ├── FindAndReplace.java
│   ├── Model.java
│   ├── Paragraph.java
│   ├── Print.java
│   ├── QuickAccess.java
│   ├── TopParent.java
│   └── module-info.java
images/
├── bgColor.png
├── bold.png
├── ... (other icons used in the application)
```

## Dependencies

- Java Desktop module (`java.desktop`) for Swing components.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes.

## Contact

For questions or support, contact [your-email@example.com](mailto:naolaboma@gmail.com).
