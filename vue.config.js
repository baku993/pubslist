module.exports = {
  lintOnSave: false,
  outputDir: __dirname + "/src/main/webapp/dist",
  configureWebpack: {
    resolve: {
      alias: {
        "@": __dirname + "/src/main/webapp/"
      }
    },
    entry: {
      app: "./src/main/webapp/main.js"
    }
  }
};
