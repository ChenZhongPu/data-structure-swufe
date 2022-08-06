set terminal pngcairo transparent size 1000,1000 enhanced font 'Verdana,20'
set out "hash-time.png"
set grid
set notitle
# set nokey
set yrange [-10:]
set xlabel 'n'
set ylabel 'time (ms)'
set linetype 1 lc rgb '#E41A1C' lw 2 # red
set linetype 2 lc rgb '#0072B2' lw 2 # blue 
plot 'st_time.txt' u 1:2 with linespoints ls 1 t 'Linked list', '' u 1:3 with linespoints ls 2 t 'Seperate chaining hashing'