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
	},
	pwa: {
		name: 'Pubs List',
		themeColor: '#916d13',
		msTileColor: '#9a9a9a',
		iconPaths: {
			msTileImage: 'img/icons/mstile-150x150.png'
		}
	}
};
