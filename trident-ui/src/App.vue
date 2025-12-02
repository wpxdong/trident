<template>
  <div id="app">
    <div class="page-nav"></div>
    <div class="page-content">
      <router-view></router-view>
    </div>
  </div>
</template>
<script lang="ts"></script>

<style scoped lang="scss">
@import 'scss/_reset.scss';
@import 'scss/_common.scss';
html,
body {
  height: 100%;
  width: 100%;
  overflow: hidden;
  margin: 0;
  padding: 0;
}
#nav {
  height: 100%;
}
#app {
  font-family:
    'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑,
    Arial, sans-serif;
  -moz-osx-font-smoothing: antialiased;
  -webkit-font-smoothing: antialiased;
  height: 100%;
  width: 100%;

  &.is-collapse {
    .page-nav {
      width: 64px;
      .header-info {
        display: none;
      }
      .toggle-btn {
        width: 24px;
        text-align: center;
      }
      .el-menu {
        .el-subment__title {
          text-overflow: unset;
        }
      }
    }
    .page-content {
      margin-left: 64px;
      width: calc(100% - 64px);
    }
  }
  .page-nav {
    position: absolute;
    left: 0;
    top: 0;
    width: 220px;
    height: 100%;
    vertical-align: top;
    box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.1);
  }
  .page-content {
    margin-left: 220px;
    width: calc(100% - 220px);
    height: 100%;
  }
  .fa,
  .fas {
    color: $FONT_COLOR;
    font-size: 12px;
  }
}
</style>
