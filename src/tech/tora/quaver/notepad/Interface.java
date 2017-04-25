package tech.tora.quaver.notepad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.list.QuickList;
import tech.tora.quaver.list.QuickListNode;
import tech.tora.quaver.list.SelectionList;
import tech.tora.quaver.list.SelectionListNode;
import tech.tora.quaver.notepad.widget.AddButton;

public class Interface extends JFrame {

	/**
	 * Serial UUID
	 */
	private static final long serialVersionUID = 1L;

	// Window Management

	JPanel wrapperPane;
	JPanel leftWrapper;
	JPanel rightWrapper;

	JPanel notebookPane;
	JPanel notebookTopPane;
	JLabel notebookTopLabel;
	JPanel notebookBotPane;

	JPanel notesPane;
	JPanel notesTopPane;
	JLabel notesTopTitle;
	JLabel notesTopRightFill;
	JPanel notesBotPane;
	
	JPanel editAreaTopPane;
	
	JEditorPane previewArea;
	JScrollPane editScroll;
	DefaultCaret caret2;

	JTextArea editArea;
	
	QuickList notebooksList;
	SelectionList notesList;
	
	public Interface() {
		if (initialiseNotebookAndNotes()) {
			initLayout();
			genHooks();
		} else 
			Launcher.exit(1, "Failed to generate UI");
	}
	
	private boolean initialiseNotebookAndNotes() {
		return true;
	}
	
	private void initLayout() {

		notebooksList = new QuickList("Notebooks");
		notesList = new SelectionList();

		// Notebook Pane
		notebookPane = new JPanel(new BorderLayout());
		notebookPane.setBackground(new Color(230, 230, 230));
		notebookPane.add(notebooksList, BorderLayout.CENTER);

		notebookTopLabel = new JLabel("Notebooks | Tags");
		notebookTopLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
		notebookTopLabel.setHorizontalAlignment(JLabel.CENTER);
		notebookTopLabel.setPreferredSize(new Dimension(200, 30));
		
		notebookTopPane = new JPanel(new BorderLayout());
		notebookTopPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(140, 140, 140)));
		notebookTopPane.setBackground(new Color(230, 230, 230));
		notebookTopPane.add(notebookTopLabel, BorderLayout.NORTH);
		notebookPane.add(notebookTopPane, BorderLayout.NORTH);
		
		notebookBotPane = new JPanel();
		notebookBotPane.setBackground(new Color(230, 230, 230));
		notebookPane.add(notebookBotPane, BorderLayout.SOUTH);
		
		notesPane = new JPanel(new BorderLayout());
		notesPane.setBackground(new Color(230, 230, 230));
		notesPane.add(notesList, BorderLayout.CENTER);

		notesTopTitle = new JLabel("Java Notebook");
		notesTopTitle.setFont(new Font("Helvetica", Font.BOLD, 14));
		notesTopTitle.setHorizontalAlignment(JLabel.CENTER);
		notesTopTitle.setPreferredSize(new Dimension(240, 30));
		
		notesTopRightFill = new JLabel("");
		notesTopRightFill.setFont(new Font("Helvetica", Font.PLAIN, 14));
		notesTopRightFill.setHorizontalAlignment(JLabel.CENTER);
		notesTopRightFill.setPreferredSize(new Dimension(30, 30));
		
		notesTopPane = new JPanel(new BorderLayout());
		notesTopPane.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(140, 140, 140)));
		notesTopPane.setBackground(new Color(230, 230, 230));
		
		notesTopPane.add(new AddButton() {
			private static final long serialVersionUID = 1L;
			@Override
			public void onMouseClick() {
				System.out.println("Create New Note for [NOTEBOOK HERE]");
			}
		}, BorderLayout.WEST);
		notesTopPane.add(notesTopTitle, BorderLayout.CENTER);
		notesTopPane.add(notesTopRightFill, BorderLayout.EAST);
		notesPane.add(notesTopPane, BorderLayout.NORTH);
		
		notesBotPane = new JPanel();
		notesBotPane.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(140, 140, 140)));
		notesPane.add(notesBotPane, BorderLayout.SOUTH);
		
		leftWrapper = new JPanel(new BorderLayout());
		leftWrapper.add(notebookPane, BorderLayout.WEST);
		leftWrapper.add(notesPane, BorderLayout.EAST);
		
		// Notes Section

		editArea = new JTextArea();
		editArea.setFont(new Font("Helvetica",Font.PLAIN,14));
		editArea.setBackground(new Color(230, 230, 230));
		editArea.setMargin(new Insets(15, 15, 15, 15));
		editArea.setLineWrap(true);
//		editArea.setText("![](http://wp.wpi.edu/library/files/2014/11/1200px-LaTeX_logo.svg_-e1415633750137.png)\n*Inspired by my professor Nghiem Quoc Minh*\n\n\n## Table of Contents\n* [What is LaTeX?](#what-is-latex)\n* [Why use LaTeX?](#why-use-latex)\n* [Set up for LaTeX](#set-up-for-latex)\n* [First LaTeX file](#first-latex-file)\n* [A deeper look](#a-deeper-look)\n* [Multilingual usage](#multilingual-usage)\n* [Lists](#lists)\n* [Paragraph and section](#paragraph-and-section)\n* [Making a table of contents](#making-a-table-of-contents)\n* [Footnotes](#footnotes)\n* [What is a package?](#what-is-a-package)\n* [Table](#table)\n* [Adding images](#adding-images)\n* [Insert code into LaTeX](#insert-code-into-latex)\n* [Additional Tools](#additional-tools)\n\n## What is LaTeX?\n\nLaTeX, which is pronounced �Lah-tech� or �Lay-tech� (to rhyme with �blech�), is a document preparation system for high-quality typesetting. It is most often used for medium-to-large technical or scientific documents but it can be used for almost any form of publishing.\n\n## Why use LaTeX?\n\n* LaTeX is free, multiplatform.\n* LaTeX is just a text document (which can be opened by any text editor), readily converted to PDF.\n* LaTeX separates content and style. Style once, then focus on content.\n* The workflow is faster compared to MS Word.\n* LaTeX is widely used for scientific topics.\n* LaTeX is simply the best option when it comes to typesetting math expressions.\n\n> LaTeX doesn't come without drawbacks, but is still worth learning.\n\n## Set up for LaTeX\n\nYou will need the following things:\n\n\n1. *LaTeX Distribution.*\nI am using [MiKTeX](https://miktex.org/about) for Windows.\n2. *LaTeX Editor.*\nI am using [TeXMaker](http://www.xm1math.net/texmaker/) for easy editing, although any text editor can create or change a LaTeX file.\n3. *PDF viewer.* (optional)\nAny PDF viewer out there is fine. This is for viewing your result.\n\nIn addition, you need to choose a [compiler](#additional-tools). The default compiler of most\neditors is pdfLaTeX, but if you need support for Unicode or TTF/OTF fonts from\nyour system, use LuaLaTeX.\n\nOr you can choose a simple online solution like [ShareLaTeX](https://www.sharelatex.com/).\nPlease look at [Additional Tools](#additional-tools) for a wider variety of choices.\n\n## First LaTeX file\n\nLet's do the traditional **Hello World** in **LaTeX**.\nIf you have installed **TexMaker**, first create a new file with ending `.tex`. Then type in the following code below to render \"Hello World!\" and run \"quick build\". For other LaTeX editors, it should also be easy to follow the same procedure.\n\n```tex\n\\documentclass[a4paper]{article}\n\n\\begin{document}\n\nHello World !  % This is your content\n\n\\end{document}\n```\n\nIt should look like this in TexMaker:\n![](http://i.imgur.com/ZuD5N6U.png)\n\n## A deeper look\n\n:eyes: A deeper look into your first LaTeX file easily shows that :\n* The first line tells the Interpreter that you are working on an **article** with the size of the a4. Other types of document you might be working with in the future are **report**, **book**... and so on.\n* A document is wrapped by the **\\begin{document}** and **\\end{document}** . Think of this as the heart of the document, as the `main()` in *java* or *C++* ... without which the document can't be rendered. \n* The part between begin and end ( which, in this case, is `Hello World` ) is simply your own content.\n* A **percent sign** (%) denotes your comment, which LaTeX will ignore.\n\n#### :zap: Attention :zap: \n\n* Looking back at **\\begin{document}** , **\\end{document}** , **\\documentclass[a4paper]{article}** . You may notice the pattern now. These are called **Typesetting Commands** ( which are usually preceded by \u201C\\\u201D ) and **arguments** ( placed inside curly braces \u201C{}\u201D ). LaTeX are basically normal texts, but powered by these commands.\n* While you are following this guide, everything will work smoothly. However, in the future, should there be any problems, **don't panic**. The error reports are human-friendly and readable. If you can't resolve them, a search tool like Google may be your best friend.  \n* Some characters are **predefined with special meanings in LaTeX. You may want to use backslashes (\\\\) in front of these characters for proper output.**  \n\n![](http://i.imgur.com/9d0bXHH.png)   \n\n## Multilingual usage\n\n**Some languages won't work right out of the box. To use TeX with other languages, you have some options.**  \n\n#### :white_check_mark: First method :white_check_mark:\n\nThe first method is including [\"packages\"](#what-is-a-package) (You will learn about it later) because pdfLaTeX, the default compiler, is limited to 256 characters and various encoding issues. For example:  \n\n```tex\n\\documentclass[a4paper]{article}\n\n\\usepackage[T5]{fontenc}\n\\usepackage[utf8]{inputenc}\n\n\\begin{document}\n\nXin ch�o th? gi?i. This is Hello World in Vietnamese.\n\n\\end{document}\n```\n\nHere we use the packages `usepackage[T5]{fontenc}` and `usepackage[utf8]{inputenc}` . This is really simple to understand as the package will import font encoders to display your content correctly. If you are using TexMaker this is what the above code display :\n\n![](http://i.imgur.com/UQEewYi.png)\n\nvs without the packages :package::  \n\n![](http://i.imgur.com/xvzrQX2.png)  \n\n:umbrella: A tricky situation is dealing with Chinese-Japanese-Korean. Here, `usepackage{CJKutf8}` with `\\begin{CJK}{UTF8}` and `\\end{CJK}` comes in very handy. Here's Japanese :jp: : \n```tex\n\\documentclass[a4paper]{article}\n\n\\usepackage{CJKutf8}\n\n\\begin{document}\n\n\\begin{CJK}{UTF8}{min}\n???????????????????????\n%Hello, I love you all.\n\\end{CJK} \n\n\n\\end{document}\n```\n\nAs easy as eating :sushi: and :bento: : \n\n![](http://i.imgur.com/Td2LmGz.png)  \n\n#### :white_check_mark: Second method :white_check_mark:\nAnother method is achievable if you switch your TeX compiler to [LuaLaTeX](#additional-tools) (or [XeLaTeX](#additional-tools)). Using `fontspec` and `polyglossia`, Unicode will work out of the box:\n\n```tex\n\\documentclass[a4paper]{article}\n\n\\usepackage{fontspec}\n\\usepackage{polyglossia}\n%\\setmainfont[]{DejaVu Serif}\n\n\\begin{document}\n\nXin ch�o th? gi?i. This is Hello World in Vietnamese.\n\n\\end{document}\n```\n\nThe default font (Latin Modern) does not support all characters. You can, however, use almost any font installed on your system by uncommenting the `\\setmainfont` line. (TTF and OTF fonts are fully supported).\n\n\n## Lists\n\n:straight_ruler: It is very important to organize your document well. Thus, let's start by putting your items into a list.  \nTwo common types of lists are **unordered** and **ordered** list. Each of them can be handled with ease in LaTeX document :  \n* Unordered List  \nUnordered list only needs **\"itemize\"**. (pun intended)\n```tex\n\\begin{itemize}\n\\item Item.\n\\item Another Item.\n\\end{itemize}\n```  \n* Ordered List  \nOrdered list, however, need us to **\"enumerate\"** them. (pun intended)  \n```tex\n\\begin{enumerate}\n\\item First Item.\n\\item Second Item.\n\\end{enumerate}\n```\n\nHere's how two types of list display in the output: \n\n![](http://i.imgur.com/jzN4RWm.png)\n\n## Paragraph and section\n\n:blue_book: We begin a section with `\\section` and a paragraph with `\\paragraph` .\n:orange_book: You can also add subsection with `\\subsection` and subparagraph with `\\subparagraph`\n\n![](http://i.imgur.com/qKbZYnG.png)\n\n## Making a table of contents\n\n:metal: It's useful to open sections and subsections with a `\\tableofcontents`\n\nExample:\n\n![](http://i.imgur.com/TBUOTRj.png)\n\n:bangbang: **Tips** : you can use `\\newpage` if you want to make a new page.\n\n## Footnotes\n\nIt's as easy as pie to use footnote+label+ref to make all kinds of footnotes you want. For example:\n```tex\nHi let me introduce myself\\footnote{\\label{myfootnote}Hello footnote}.\n... (later on)\nI'm referring to myself \\ref{myfootnote}.\n```\n:point_down: :point_down: Can you see it ?  :point_down: :point_down:\n\n![](http://i.imgur.com/BSYPX4C.png)\n\n:bangbang: **Tips** : you can use `\\newline` to make a new line.\n\n## What is a package?\n\nLaTeX offers a lot of functions by default, but in some situations it can come in handy to use so called packages. To import a package in LaTeX, you simply add the `\\usepackage` :package:\n\nHere is an example of using two packages for displaying math:\n\n![](http://i.imgur.com/050nrfh.png)  \n\nEven more epic is how circuits are displayed: \n\n![](http://i.imgur.com/If4lbLA.png)\n\n:construction: You should google search more if you want a package that meets your requirements. For example, amsmath is widely used for math and has a lot of extension typeset for math, circuitikz is for circuits designing, etc.. Covering them all would be impossible for this general guide.\n\n## Table\n\nA practical example :thought_balloon: :\n\n```tex\n\\begin{table}[h!]\n  \\centering\n  \\caption{Caption for the table.}\n  \\label{tab:table1}\n  \\begin{tabular}{l|c||r}\n    1 & 2 & 3\\\\\n    \\hline\n    a & b & c\\\\\n  \\end{tabular}\n\\end{table}\n```\n\n:star2: This is what it renders :star2: :\n\n![](http://i.imgur.com/XbZJJ2E.png)\n\nNow let's take a closer look :eyes: :\n\n* For tables, first we need a table environment, which is why we have `\\begin{table}` and `\\end{table}` .\n* You will learn about h! later in the image section. It goes with `\\centering` to keep the table at the center of the page.\n* Caption is for describing. Label is for tagging. You will see these more in image section.\n* Tabular is the most important part. A table environment always needs a tabular environment inside.\n  - the part `{l|c||r}` is where we format the content inside the table. Here we can see :\n    * l or c or r means that the content inside each cell will be left-aligned or center-aligned or right-aligned, respectively.\n    * the vertical slash | or || is actually the format of the vertical lines/borders inside the table's columns.\n  - 1 & 2 & 3 => 1 2 3 are the contents of each cells. the ampersand & is used to separate the content of each cell in a row.\n  - a `\\hline` actually adds a horizontal line to separate each row.\n\n:bangbang: **Tips** You can use a package :package: called booktabs `\\usepackage{booktabs}` for a visually better table.\n\n## Adding images\n\nTo add an image to the LaTeX file , you need to use figure environment and the graphicx package. Use `\\usepackage{graphicx}` and\n\n```tex\n\\begin{figure}\n  \\includegraphics[width=\\linewidth]{filename.jpg}\n  \\caption{What is it about?}\n  \\label{fig:whateverlabel}\n\\end{figure}\n```\n\n:bangbang: **Tips**: Put [width=\\linewidth] to scale the image to the width of the document.  If you want to float the image, then you need to attribute the begin with a certain value. Also, the fig is for later reference so name it with care.\n\n```tex\n\\begin{figure}[h!]\n```\n\n:passport_control: Legit values are :\n\n* h (here) - same location\n* t (top) - top of page\n* b (bottom) - bottom of page\n* p (page) - on an extra page\n* ! (override) - will force the specified location\n\nHere's how the image is rendered :\n\n![](http://i.imgur.com/ysY9MOb.png)\n\n## Insert code into LaTeX\n\n#### :white_check_mark: First method :white_check_mark:\n\nOne aspect of text compiling that is of the utmost importance to programmers and developers is how to professionally insert codes into the document.\n\nFor LaTeX, the process is simple and very professional. We just wrap the code with some predefined content, then we are good to go.\n\nExample :\n\n```tex\n\\documentclass[a4paper]{article}\n\n\\begin{document}\n\nHello world!\n\n\\begin{verbatim}\n#include <iostream>\n\nint main()\n{\n\tstd::cout << \"hello world!\\n\";\n\treturn 0;\n}\n\\end{verbatim}\n\n\\end{document}\n```\n\n:speech_balloon: **LaTeX supports syntax for these languages** :speech_balloon:\n\n![](http://i.imgur.com/FJfj8Er.png)\n\nAs you can see, with the **{verbatim}** wrapper you can easily insert code without worrying about how the syntax is formatted. Here is how it looks out of the box, clean and professional :\n\n![](http://i.imgur.com/tpercup.png)\n\n#### :white_check_mark: :white_check_mark: Second Method :white_check_mark: :white_check_mark:\n\nThis method gives you more options, including insert code **inline**, make **custom styles** code, choose a **specific language** for code, **import code** **from** another **file** within the same directory.... With this method, you dont use **{verbatim}** , but include a package :package: named **listings**.\n\nConsider the following example :\n```tex\n\\documentclass[a4paper]{article}\n\n\\usepackage{listings}\n\\usepackage{color}\n\n\\lstdefinestyle{mystyle}{\nkeywordstyle=\\color{magenta},\nbackgroundcolor=\\color{yellow},\ncommentstyle=\\color{green},\nbasicstyle=\\footnotesize,\n}\n\\lstset{style=mystyle}\n\n\\begin{document}\n\n\nHello world!\n\n\\begin{lstlisting}[language=Python]\n\nprint \"Hello World!\"\n\n\\end{lstlisting}\n\n\\lstinputlisting[language=C++]{hello.cpp}\n\nLorem ipsum dolor sit amet \\lstinline{print \"Hello World\"} , consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n\n\n\\end{document}\n\n```\nFrom this, you can see:\n\n1. To insert a code block , start with `\\begin{lstlisting}` and end with `\\end{lstlisting}`\n2. To import code from another file within the same directory, you can use `lstinputlisiting{name_of_file}`\n3. To specify a language for the code, use `[language=C++]`\n4. To insert inline code use `\\lstinline`\n5. To apply custom styles, use the `\\usepackage{color}` and define your own style then define the listing with your own theme (Please look at code below). You can modify many things with your own style, but you need to read the doc for the correct property name.\n6. Interested ?? More [here](https://en.wikibooks.org/wiki/LaTeX/Source_Code_Listings).\n\nHere is how the code above compiles in TexMaker :\n\n![](http://i.imgur.com/XwwDJNo.png)\n\n## Multiple files in LaTeX  \n\nWhen we use LaTeX, we may face a problem that a document is too long to be handle. Therefore, we should divide the file so that its contents can be easily handled.\n\nLet's look at the example:\n\n```tex\n% main.tex\n\\documentclass[a4paper]{article}\n\n\\begin{document}\n\nHello Latex, This is my first part.\n\nHello Latex, This is my second part.\n\n\\end{document}\n```\n\nIt's just a normal LaTeX file. Now, let's divide the document into two parts using the `\\input` keyword:\n\n\n```tex\n% main.tex\n\\documentclass[a4paper]{article}\n\n\\begin{document}\n\nHello Latex, This is my first part.\n\n\\input{second_file}\n\n\\end{document}\n```\n\n\n```tex\n% second_file.tex\nHello Latex, This is my second part.\n```\n\nNow the main file looks different, but better documented. Here is the result in TexMaker:\n\n[![multi_file.png](https://s14.postimg.org/deg0kqhu9/multi_file.png)](https://postimg.org/image/hnkqmwl3h/)\n\n:bangbang: **Tips** : For readability, clarity and maintenance purpose, it is highly suggested that you divide your Main file systematically, hierarchically and scientifically. Don't divide without reasons or you may get a mess later.  \n\n## Additional Tools\n\n#### Distributions\n\n* [MiKTeX](https://miktex.org/about) for Windows.\n* [TeX Live](https://www.tug.org/texlive/) for Linux and Unix-based.\n* [MacTeX](https://tug.org/mactex/) for macOS.\n* [ShareLaTeX](https://www.sharelatex.com/) \u2014 an online editor.\n* [Overleaf](https://www.overleaf.com/) \u2014 an collaborative online editor.\n* [StackEdit](https://stackedit.io/) - In-browser markdown editor.\n\n#### LaTeX Editors\n\n* [TeXMaker](http://www.xm1math.net/texmaker/) Cross platform LaTeX editor.\n* [TeXStudio](http://www.texstudio.org/) An enhanced fork of TeXMaker with more features.\n* TeXShop and TeXworks (minimal editors)\n\n#### LaTeX Compilers\n\n* Most editors will have an option for you to change the default compiler. Here's an example : \n\n![](http://i.imgur.com/FbNUiL7.png)\n\n## HOORAY !!\n\n:tada: Thank you for finishing the guide. That's basically all you need to know about LaTeX. :hammer:  \nIf you are greatly interested, more on LaTeX can be found [here](http://www.latex-project.org/help/documentation/) or all over the web, depending on your need.\n\n## License\n\n![](http://www.wtfpl.net/wp-content/uploads/2012/12/wtfpl-badge-1.png)\nhttp://www.wtfpl.net/\n\n**DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE**\nCopyright (C) 2016 Luong Vo\nEveryone is permitted to copy and distribute verbatim or modified copies of this license document, and changing it is allowed as long as the name is changed.\nTERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION : You just DO WHAT THE FUCK YOU WANT TO.");

		editArea.setBorder(new InsetsBorder(new Insets(5, 10, 5, 5)));

		editScroll = new JScrollPane(editArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		editScroll.setBorder(new InsetsBorder(new Insets(0, 0, 0, 0)));
		
		previewArea = new JEditorPane();
		caret2 = (DefaultCaret)previewArea.getCaret();
		caret2.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		previewArea.setMargin(new Insets(0, 0, 0, 0));
		previewArea.setAutoscrolls(false);
		previewArea.setContentType("text/html");
		previewArea.setText("<html><head><title>" + "TITLE" + "</title>"
				+ "</head><body style=\"background-color: #2a3137; color: #f2f2f2; font: helvetica; padding: 20px;\">" 
				+ "<h1 style=\"color: #FFFFFF;\">"+ "TITLE" +"</h1><hr><br><div style=\"\"></div></body></html>");

//		JScrollPane previewScroll = new JScrollPane(previewArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		editAreaTopPane = new JPanel();
		editAreaTopPane.setBackground(new Color(230, 230, 230));
		
		rightWrapper = new JPanel(new BorderLayout());
		rightWrapper.setBackground(new Color(140, 140, 140));
		rightWrapper.add(editAreaTopPane, BorderLayout.NORTH);
		rightWrapper.add(editScroll, BorderLayout.CENTER);
		
		wrapperPane = new JPanel(new BorderLayout());
		wrapperPane.setBackground(new Color(100, 100, 100));
		wrapperPane.add(leftWrapper, BorderLayout.WEST);
		wrapperPane.add(rightWrapper, BorderLayout.CENTER);
		
		setContentPane(wrapperPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setTitle("Quaver");
		setVisible(true);

		setSize(1280, 800);
	}
	
	public void genHooks() {

		notebooksList.addNode(new QuickListNode(notebooksList, "", "Java Notebook", "4"));
		notebooksList.addNode(new QuickListNode(notebooksList, "", "Linux Handbook", "0"));
		notebooksList.addNode(new QuickListNode(notebooksList, "", "Research Notes", "23"));
		
		notesList.addNode(new SelectionListNode(notesList, "Seeker", "10 Jan, 2016"));
		notesList.addNode(new SelectionListNode(notesList, "Spirit", "24 Mar, 2014"));
		notesList.addNode(new SelectionListNode(notesList, "Sage", "10 Jun, 2010"));
		notesList.addNode(new SelectionListNode(notesList, "True", "14 Jan, 2013"));
		
	
		notebookBotPane.add(new AddButton() {
			private static final long serialVersionUID = 1L;
			@Override
			public void onMouseClick() {
				System.out.println("Add Button Clicked");
			}
		});
		
		editArea.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// Do Nothing
			}

			@Override
			public void keyReleased(KeyEvent e) {
				updatePreview();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getModifiers() == Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()) {
					if (e.getKeyCode() == KeyEvent.VK_S) {
						System.out.println("Saving");
						save();
					}
				}
			}
		});
		
	}
	
	public void updatePreview() {
		
	}
	
	public void save() {
		
	}
	
}
