const FileManagerPlugin = require("filemanager-webpack-plugin");

module.exports = {
  lintOnSave: false,
  outputDir: __dirname + "/src/main/webapp/dist",
  devServer: {
    proxy: {
      "^/api": {
        target: "http://localhost:80",
        ws: true,
        changeOrigin: true
      }
    }
  },
  configureWebpack: {
    resolve: {
      alias: {
        "@": __dirname + "/src/main/webapp/"
      }
    },
    entry: {
      app: "./src/main/webapp/main.js"
    },
    plugins: [
      new FileManagerPlugin({
        onEnd: [
          {
            copy: [
              {
                source: __dirname + "/src/main/webapp/dist",
                destination: __dirname + "/build/resources/main/static"
              }
            ]
          }
        ]
      })
    ]
  }
};
