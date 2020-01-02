module.exports = {
	lintOnSave: true,
	outputDir: __dirname + '/src/main/webapp/dist',
	devServer: {
		proxy: {
			'^/api': {
				target: 'http://localhost:80',
				ws: true,
				changeOrigin: true
			}
		}
	},
	configureWebpack: {
		resolve: {
			alias: {
				'@': __dirname + '/src/main/webapp/'
			}
		},
		entry: {
			app: './src/main/webapp/main.js'
		}
	}
};
