mdbook build
cd book
git init
git branch -M pages
touch .gitignore
echo ".DS_Store" >>.gitignore
git add .
git commit -m 'deploy'
git remote add origin git@github.com:ChenZhongPu/data-structure-swufe.git
git push -u -f origin pages
