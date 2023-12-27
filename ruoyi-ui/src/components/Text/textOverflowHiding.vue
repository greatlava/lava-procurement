<template>
  <div>
    <el-tooltip v-if="textLen>width" class="item" effect="dark" :content="text" placement="top">
      <el-button type="text" class="content" size="mini" :style="{width:width+'px'}">{{text}}</el-button>
    </el-tooltip>
    <div v-else  class="content" :style="{width:width+'px'}">{{text}}</div>
  </div>

</template>

<script>
export default {
  name: 'loneText',

  props: {
    text: {
      type: String,
      default: ''
    },
    width: {
      type: Number,
      default: 100
    }
  },
  computed: {
    textLen() {
      let len = 0
      for (var i = 0; i < this.text.length; i++) {
        if (this.text.charCodeAt(i) > 127 || this.text.charCodeAt(i) == 94) {
          len += 2;
        } else {
          len++;
        }
      }
      return len * 8
    }
  },

  data() {
    return {
    }
  },

  methods: {
  }
}
</script>
<style scoped>
.content {
  font-size: 14px;
  color: #a1a1a1;
  text-align: left;
  background-color: transparent;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  display: inline-block;
  padding: 0;
}
</style>
