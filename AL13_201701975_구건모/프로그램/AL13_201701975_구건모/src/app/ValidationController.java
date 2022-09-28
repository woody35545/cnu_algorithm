package app;

import java.io.File;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import fileIO.ExtendedBufferedOutputStream;

public class ValidationController {
	private File _firstFile;
	private File _secondFile;

	private String _firstFilePath;
	private BufferedInputStream _firstInputStream;
	private BufferedInputStream _secondInputStream;

	private File firstFile() {
		return this._firstFile;
	}

	private void setFile(File newFirstFile) {
		this._firstFile = newFirstFile;
	}

	private File secondFile() {
		return this._secondFile;
	}

	private String firstFilePath() {
		return this._firstFilePath;
	}

	private void setFirstFilePath(String newFirstFilePath) {
		this._firstFilePath = newFirstFilePath;
	}

	private BufferedInputStream firstInputStream() {
		return this._firstInputStream;
	}

	private void setFirstInputStream(BufferedInputStream newFirstInputStream) {
		this._firstInputStream = newFirstInputStream;
	}

	private void setSecondStream(BufferedInputStream newSecondInputStream) {
		this._secondInputStream = newSecondInputStream;
	}
	
	private boolean initFirstFile() {
		return false;
	}

}
