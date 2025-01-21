import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'
import vuetify from 'vite-plugin-vuetify';

const getAliasPath = (path) => {
  return fileURLToPath(new URL(path, import.meta.url));
};

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue(),vueDevTools(),vuetify({autoImport: true})],
  resolve: {
    alias: {
      "@": getAliasPath("./src"),
      "@assets": getAliasPath("./src/assets"),
      "@components": getAliasPath("./src/components"),
      "@view": getAliasPath("./src/view"),
      "@moduels" : getAliasPath("./src/moduels"),
    },
  },
  // scss 전역 사용
});
