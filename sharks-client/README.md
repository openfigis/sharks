




# sharks-client

This project is generated with [yo angular generator](https://github.com/yeoman/generator-angular)
version 0.11.1.

## installing nodejs
install nodejs by downloading it. Nodejs comes in stable or LTS version, so far Sharks runs on both. 
 
Then run on the CLI: 
npm install 
  
 



  

## NodeJs, npm and bower

Here how to install [NodeJs](https://nodejs.org/) and [npm](https://www.npmjs.com/) in Ubuntu:


	curl -sL https://deb.nodesource.com/setup | sudo bash -
	sudo apt-get install nodejs

Setup for global installation ([source](http://stackoverflow.com/questions/18212175/npm-yeoman-install-generator-angular-without-sudo/18277225#18277225)):

	echo prefix = ~/.node >> ~/.npmrc
	
Edit '~/.bashrc' and add:

	export PATH="$PATH:$HOME/.node/bin"
	NODE_PATH="$NODE_PATH:$HOME/.node/lib/node_modules"
	
[Bower](http://bower.io/) global installation:

	sudo npm install -g bower
	
	





## installing grunt
Sharks depends on Grunt. Installing grunt can be done by installing nodejs, subsequently: 
npm install grunt-autoprefixer

Problems like 
>> Local Npm module "grunt-autoprefixer" not found. Is it installed?
may occur. 

I fixed them one by one with these commands:
npm install grunt-autoprefixer
(I am sure there should be a more intelligent way of doing this)


## Build & development

Run `grunt` for building and `grunt serve` for preview. Do note that http://0.0.0.0:9000 often does not work in your browser, go instead to [http://localhost:9000](http://localhost:9000). 

## Testing

Running `grunt test` will run the unit tests with karma.
