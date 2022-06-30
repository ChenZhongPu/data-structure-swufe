set terminal pngcairo transparent size 1000,1000 enhanced font 'Verdana,20'
set out "output.png"
set grid
set notitle
set nokey
set xlabel 'n'
set ylabel 'time (ms)'
set linetype 1 lc rgb '#E41A1C' lw 2 # red
plot 'time.txt' u 1:2 with linespoints ls 1