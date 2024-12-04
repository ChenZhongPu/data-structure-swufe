import { exec } from "child_process";
import { readdir } from "fs/promises";
import path from "path";

async function buildSlidevPresentation(file) {
  const name = path.basename(file, ".md");
  const command = `slidev build ${file} --base /${name}/ --out dist/${name}`;

  return new Promise((resolve, reject) => {
    exec(command, (error, stdout, stderr) => {
      if (error) {
        console.error(`Error building ${file}:`, error);
        resolve(null); // 继续执行，而不是中断整个过程
        return;
      }
      if (stderr) {
        console.error(`Error output for ${file}:`, stderr);
        resolve(null);
        return;
      }
      console.log(`Successfully built ${file}:`, stdout);
      resolve(stdout);
    });
  });
}

async function buildAllPresentations() {
  try {
    // 获取当前目录中的所有 .md 文件
    const files = (await readdir(".")).filter(
      (file) => path.extname(file) === ".md",
    );

    // 串行处理，但使用 Promise 以便更好地处理异步
    for (const file of files) {
      await buildSlidevPresentation(file);
    }

    console.log("All presentations built successfully!");
  } catch (error) {
    console.error("Error during build process:", error);
  }
}

// 执行构建
buildAllPresentations();
