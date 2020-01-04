const CopyPlugin = require('copy-webpack-plugin');
const path = require('path');

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
	chainWebpack: config => {
		config.plugin('html').tap(args => {
			args[0].template = path.resolve(__dirname, 'src/main/webapp/public/index.html');
			return args;
		});
	},
	configureWebpack: {
		resolve: {
			alias: {
				'@': __dirname + '/src/main/webapp/'
			}
		},
		entry: {
			app: './src/main/webapp/main.js'
		},
		plugins: [
			new CopyPlugin([
				{
					from: 'src/main/webapp/public/img/icons',
					to: path.resolve(__dirname, 'src/main/webapp/dist/img/icons'),
					flatten: true,
				},
				{
					from: 'src/main/webapp/public',
					to: path.resolve(__dirname, 'src/main/webapp/dist'),
					flatten: true,
					ignore: ['index.html', '*.png', '*.jpg']
				},
			]),
		]
	},
	pwa: {
		name: 'Pubs List',
		themeColor: '#1976d2',
		msTileColor: '#9a9a9a',
		iconPaths: {
			msTileImage: 'img/icons/mstile-150x150.png'
		}
	}
};
