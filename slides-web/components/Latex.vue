<template>
  <slot></slot>
</template>

<script setup lang="js">
import { onBeforeMount, onMounted } from "vue";

// 动态加载样式表和脚本
const loadExternalResource = (tag, url, attributes = {}) => {
  return new Promise((resolve, reject) => {
    const element = document.createElement(tag);
    element.onload = resolve;
    element.onerror = reject;

    // 设置额外的属性
    Object.keys(attributes).forEach((attr) => {
      element.setAttribute(attr, attributes[attr]);
    });

    if (tag === "link") {
      element.rel = "stylesheet";
      element.href = url;
    } else if (tag === "script") {
      element.src = url;
    }
    document.head.appendChild(element);
  });
};

onBeforeMount(async () => {
  await loadExternalResource(
    "link",
    "https://cdn.jsdelivr.net/npm/pseudocode@2.4.1/build/pseudocode.min.css",
  );
  await loadExternalResource(
    "script",
    "https://cdn.jsdelivr.net/npm/pseudocode@2.4.1/build/pseudocode.min.js",
  );
  await loadExternalResource(
    "script",
    "https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.16.7/katex.min.js",
    {
      integrity:
        "sha512-EKW5YvKU3hpyyOcN6jQnAxO/L8gts+YdYV6Yymtl8pk9YlYFtqJgihORuRoBXK8/cOIlappdU6Ms8KdK6yBCgA==",
      crossorigin: "anonymous",
      referrerpolicy: "no-referrer",
    },
  );
});

onMounted(() => {
  if (window.pseudocode) {
    window.pseudocode.renderClass("pseudocode", { lineNumber: true });
  }
});
</script>

<style scoped></style>
