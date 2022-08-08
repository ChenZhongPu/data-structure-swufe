set terminal pngcairo transparent size 1000,1000 enhanced font 'Verdana,20'
set out "front-back.png"
set grid
set notitle
# set nokey
set yrange [-0.5:]
set xlabel 'n'
set ylabel 'time (ms)'
set linetype 1 lc rgb '#E41A1C' lw 4 # red
set linetype 2 lc rgb '#0072B2' lw 4 # blue 
plot 'list_back_front.txt' u 1:2 with linespoints ls 1 t 'Front', '' u 1:3 with linespoints ls 2 t 'Back'