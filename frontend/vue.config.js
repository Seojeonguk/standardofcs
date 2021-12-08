module.exports = {
  devServer: {
    https: false,
    port: 8088,
    open: true,
    proxy: {
      "/api/v1": {
        target: "http://k5c101.p.ssafy.io/api",
        changeOrigin: true,
        logLevel: "debug",
      },
    },
    historyApiFallback: true,
    hot: true,
  },
  pluginOptions: {
    quasar: {
      importStrategy: "kebab",
      rtlSupport: false,
    },
  },
  transpileDependencies: ["quasar"],
};
