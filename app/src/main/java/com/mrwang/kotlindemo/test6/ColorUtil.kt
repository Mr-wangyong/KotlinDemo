package visioncore.kuaida.common.util

/**
 * @author hanxiao
 * @date 2017/12/31 下午3:50
 */
object ColorUtil {

  fun hsbTorgb(h: Float, s: Float, v: Float): String {
    assert(java.lang.Float.compare(h, 0.0f) >= 0 && java.lang.Float.compare(h, 360.0f) <= 0)
    assert(java.lang.Float.compare(s, 0.0f) >= 0 && java.lang.Float.compare(s, 1.0f) <= 0)
    assert(java.lang.Float.compare(v, 0.0f) >= 0 && java.lang.Float.compare(v, 1.0f) <= 0)
    var r = 0f
    var g = 0f
    var b = 0f
    val i = (h / 60 % 6).toInt()
    val f = h / 60 - i
    val p = v * (1 - s)
    val q = v * (1 - f * s)
    val t = v * (1 - (1 - f) * s)
    when (i) {
      0 -> {
        r = v
        g = t
        b = p
      }
      1 -> {
        r = q
        g = v
        b = p
      }
      2 -> {
        r = p
        g = v
        b = t
      }
      3 -> {
        r = p
        g = q
        b = v
      }
      4 -> {
        r = t
        g = p
        b = v
      }
      5 -> {
        r = v
        g = p
        b = q
      }
      else -> {
      }
    }
    return toColorString((r * 255.0).toInt(), (g * 255.0).toInt(), (b * 255.0).toInt())
  }

  private fun toColorString(r: Int, g: Int, b: Int): String {
    var red = Integer.toHexString(r).toString()
    red = if (red.length <= 1) "0$red" else red
    var green = Integer.toHexString(g).toString()
    green = if (green.length <= 1) "0$green" else green
    var blue = Integer.toHexString(b).toString()
    blue = if (blue.length <= 1) "0$blue" else blue
    return "#$red$green$blue"
  }
}