#！/bin/bash
#set -x

ENV=$1
MODE=$2

PLUGIN_URL=""
PLUGIN_NAME="pathobot"
PLUGIN_DEV_URL="http://localhost:3099/#/chat?sidebar=0"
PLUGIN_PROD_URL="/plugins/$PLUGIN_NAME/index.html#/chat?sidebar=0"
WORKSPACE=packages

cd_workspace() {
  if [ ! -d $WORKSPACE ]; then
    mkdir $WORKSPACE
  fi
  cd $WORKSPACE
}

# 下载插件代码，或者更新代码
clone_repo() {
  if [ -d $PLUGIN_NAME ]; then
    cd $PLUGIN_NAME
    git pull
    cd ..
  else
    git clone https://github.com/AI-Application-Innovation/PathbotFrontend.git $PLUGIN_NAME
    cd $PLUGIN_NAME
  fi
}

# 安装插件依赖
install_dep() {
  npm install
}

# 本地运行
run_start() {
  PLUGIN_URL=$PLUGIN_DEV_URL

  if [[ $MODE = "spa" ]]; then
    start cmd /k "npm run start:spa"
  else
    start cmd /k "npm run start"
  fi
}

# 生产构建
run_build() {
  PLUGIN_URL=$PLUGIN_PROD_URL

  if [[ $MODE = "spa" ]]; then
    npm run build:spa
  else
    npm run build
  fi
}

# 安装插件
install_plugin() {
  cp -r ./dist ../../plugins/$PLUGIN_NAME
}

# 更新插件数据
update_plugin_meta() {
  node ../../plugins/plugin.js "{\"name\":\"$PLUGIN_NAME\",\"url\":\"$PLUGIN_URL\"}"
}

echo "Start download and build plugin pathobot with mode:$MODE"

cd_workspace
clone_repo
install_dep

if [[ $ENV == "prod" ]]; then
  run_build
  install_plugin
else
  run_start
fi

update_plugin_meta