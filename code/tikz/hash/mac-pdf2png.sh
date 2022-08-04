pdfcrop $1.pdf $1.pdf
pdftocairo -png -transp -r 300 $1.pdf
mv $1-1.png $1.png