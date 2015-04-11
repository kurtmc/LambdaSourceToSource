all:
	mkdir -p bin
	pdflatex -output-directory bin report.tex
	pdflatex -output-directory bin report.tex
	mv bin/report.pdf ./

spellcheck:
	aspell --lang=en_GB --mode=tex check report.tex
