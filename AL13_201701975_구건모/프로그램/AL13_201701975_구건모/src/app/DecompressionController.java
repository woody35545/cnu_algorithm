package app;

import java.io.File;
import fileIO.ExtendedBufferedInputStream;
import fileIO.ExtendedBufferedOutputStream;
import fileIO.BitInputManager;
import huffman.HuffmanDecoder;

public class DecompressionController {
	private File _compressedFile;
	private File _decompressedFile;
	private ExtendedBufferedInputStream _compressedInputStream;
	private ExtendedBufferedOutputStream _decompressedOutputStream;
	private BitInputManager _bitInputManager;
	private HuffmanDecoder _huffmanDecoder;

	private File compressedFile() {
		return this._compressedFile;
	}

	private void setCompressedFile(File newCompressedFile) {
		this._compressedFile = newCompressedFile;
	}

	private File decompressedFile() {
		return this._decompressedFile;
	}

	private void setDecompressedFile(File newDecompressedFile) {
		this._decompressedFile = newDecompressedFile;
	}

	private ExtendedBufferedInputStream compressedInputStream() {
		return this._compressedInputStream;
	}

	private void setCompressedInputStream(ExtendedBufferedInputStream newExtendedBufferedInputStream) {
		this._compressedInputStream = newExtendedBufferedInputStream;
	}

	private ExtendedBufferedOutputStream decompressedOutputStream() {
		return this._decompressedOutputStream;
	}

	private void setDecompressedOutputStream(ExtendedBufferedOutputStream newExtendedBufferedOutputStream) {
		this._decompressedOutputStream = newExtendedBufferedOutputStream;
	}

	private BitInputManager bitInputManager() {
		return this._bitInputManager;
	}

	private void setBitInputManager(BitInputManager newBitInputManager) {
		this._bitInputManager = newBitInputManager;
	}

	private HuffmanDecoder huffmanDecoder() {
		return this._huffmanDecoder;
	}

	private void setHuffmanDecoder(HuffmanDecoder newHuffmanDecoder) {
		this._huffmanDecoder = newHuffmanDecoder;
	}

}
