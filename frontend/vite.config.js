import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

const getAliasPath = (path) => {
  return fileURLToPath(new URL(path, import.meta.url));
};

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue(),vueDevTools()],
  resolve: {
    alias: {
      "@": getAliasPath("./src"),
      "@assets": getAliasPath("./src/assets"),
      "@components": getAliasPath("./src/components"),
      "@view": getAliasPath("./src/view"),
    },
  },
  // scss 전역 사용
});
