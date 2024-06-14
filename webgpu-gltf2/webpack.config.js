const HtmlWebpackPlugin = require("html-webpack-plugin");
const path = require("path");

module.exports = {
    stats: 'none',
    entry: "./src/glb_viewer.js",
    mode: "development",
    output: {
        filename: "main.js",
        path: path.resolve(__dirname, "dist"),
    },
    module: {
        rules: [
            {
                test: /\.glb$/i,
                type: "asset/source",
            }
        ]
    },
    plugins: [new HtmlWebpackPlugin({
        template: "./index.html",
    })],
};

