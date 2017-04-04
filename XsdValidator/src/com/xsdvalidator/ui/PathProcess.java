package com.xsdvalidator.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

public class PathProcess implements ActionListener {

	private DashBoard dash;
	private String type;

	public PathProcess(DashBoard dash, String type) {
		this.dash = dash;
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser;
		final String currentDir = this.dash.getCurrentDir();
		if (currentDir == null || currentDir.isEmpty()) {
			chooser = new JFileChooser();
		} else {
			chooser = new JFileChooser(new File(currentDir));
		}

		// affichage
		chooser.showOpenDialog(null);

		// récupération du fichier sélectionné
		final File path = chooser.getSelectedFile();
		this.dash.setCurrentDir(path.getPath());
		this.dash.displayPath(path.getAbsolutePath(), this.type);

	}
}
