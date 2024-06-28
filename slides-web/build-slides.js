import { exec } from 'child_process';
import { readdir } from 'fs/promises';
import path from 'path';

// 获取当前目录中的所有 .md 文件
const files = (await readdir('.')).filter(file => path.extname(file) === '.md');

files.forEach(file => {
  // 获取文件名（不带扩展名）
  const name = path.basename(file, '.md');

  // 构建 Slidev 幻灯片并输出到指定目录
  const command = `slidev build ${file} --base /${name}/ --out dist/${name}`;
  exec(command, (error, stdout, stderr) => {
    if (error) {
      console.error(`Error building ${file}:`, error);
      return;
    }
    if (stderr) {
      console.error(`Error output for ${file}:`, stderr);
      return;
    }
    console.log(`Successfully built ${file}:`, stdout);
  });
});
