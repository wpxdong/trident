<template>
  <div class="header">
    <span class="position-tip">{{ $t('M_BREAD_CRUMB_0000') }}</span>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item v-for="(item, index) in list" :key="index">
        <router-link
          :to="item.path"
          class="item-link"
          active-class="router-active"
          v-if="item.path"
          >{{ item.name }}</router-link
        >
        <span v-else>{{ item.name }}</span>
      </el-breadcrumb-item>
    </el-breadcrumb>
    <div class="user-info">
      <el-tooltip :content="$t('M_BREAD_CRUMB_0003')" placement="bottom" effect="light">
        <el-button type="text" @click="changeLang">En/中</el-button>
      </el-tooltip>
      <el-dropdown @command="handleCommand">
        <!-- <span class="el-dropdown-link">
          Dropdown List
          <el-icon class="el-icon--right">
            <arrow-down />
          </el-icon>
        </span> -->
        <div class="el-dropdown-link">
          <el-button type="primary">
            Dropdown List<el-icon class="el-icon--right"><arrow-down /></el-icon>
          </el-button>
          <!-- <el-avatar :icon="ChatDotRound">
            <el-icon class="el-icon--right"><arrow-down /></el-icon>
          </el-avatar> -->
        </div>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item command="logout">{{ $t('M_BREAD_CRUMB_0002') }}</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>
<script lang="ts" setup>
import type { IBreadcrumbItem } from '@/vo/Layout'
// import { ChatDotRound } from '@element-plus/icons-vue'
defineProps({
  list: Array<IBreadcrumbItem>,
})
const handleCommand = () => {
  console.log('command')
}
const changeLang = () => {}
</script>
<style lang="scss" scoped>
@use 'scss/_index.scss' as *;
.header {
  height: 60px;
  padding: 0 20px;
  .position-tip {
    display: inline-block;
    line-height: 60px;
    vertical-align: top;
    font-size: 14px;
  }
  .el-breadcrumb {
    display: inline-block;
    ::v-deep .el-breadcrumb__item {
      display: inline-block;
      line-height: 60px;
    }
    .item-link {
      font-weight: normal;
      color: $THEME_COLOR;
    }
  }
  .user-info {
    display: inline-block;
    float: right;
    height: 40px;
    margin-top: 10px;
    & > .el-button {
      vertical-align: top;
      margin-right: 20px;
      line-height: 22px;
    }
    .language {
      vertical-align: top;
      line-height: 20px;
      margin-top: 10px;
    }
    ::v-deep .el-icon-user-solid {
      margin-left: 0;
      color: #fff;
    }
  }
  .el-dropdown-menu {
    ::v-deep .el-dropdown-menu__item {
      min-width: 100px;
      &:hover {
        background: $HOVER_BACKGROUND_COLOR !important;
        color: $THEME_COLOR !important;
      }
    }
  }
}
</style>
