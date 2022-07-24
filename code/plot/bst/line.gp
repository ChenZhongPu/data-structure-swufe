set terminal pngcairo transparent size 1000,1000 enhanced font 'Verdana,20'
set out "output.png"
set grid
set notitle
set nokey
set xlabel 'n'
set xrange [10:10000000]
set xtics (10, 100, 1000, 10000, 100000, 1000000, 10000000)
set logscale x
set format x '10^{%L}'
set ylabel 'height'
set linetype 1 lc rgb '#E41A1C' lw 2 # red
plot 'height.txt' u 1:2 with linespoints ls 1