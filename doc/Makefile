all:
	mkdir -p bin
	pdflatex -output-directory bin report.tex
	bibtex bin/report.aux
	pdflatex -output-directory bin report.tex
	pdflatex -output-directory bin report.tex
	mv bin/report.pdf ./

clean:
	rm -r bin/

spellcheck:
	aspell --lang=en_GB --mode=tex check report.tex
