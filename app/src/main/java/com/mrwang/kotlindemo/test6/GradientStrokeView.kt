package visioncore.kuaida.common.view

import android.animation.Animator
import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.LinearLayout
import com.example.hanxiao.kttest.half
import com.example.hanxiao.kttest.halfToFloat
import com.example.hanxiao.kttest.uiFloat
import com.example.hanxiao.kttest.uiPX
import visioncore.kuaida.common.util.ColorUtil


/**
 * @author hanxiao
 * @date 2017/12/31 下午1:43
 */
@SuppressLint("ViewConstructor")
open class GradientStrokeView(context: Context, private var animatorType: AnimatorType = AnimatorType.Run) : LinearLayout(context) {

  private val paint = Paint()
  private val ovalPaint = Paint()
  private val backgroundPaint = Paint()
  private var startValue = 0f
  private var endValue = 50f
  private var valueAnimator: ValueAnimator? = null
  private var interval = 40L
  private val totalTime = interval * 180L
  private val color = Color.parseColor("#ffffff")

  private var isInit = false
  var radius = 0f
  private var strokeWidth = 5.uiPX().toFloat()
  private var startColor: String? = null
  private var endColor: String? = null
  private var progressPath = Path()
  private var isDrawError = false
  private var pathMeasure: PathMeasure? = null
  private var pathMeasure1: PathMeasure? = null
  private var pathMeasure2: PathMeasure? = null
  private var firstPath = Path()
  private var secondPath = Path()
  private var thirdPath = Path()
  private var firstStartValue = 0f
  private var firstEndValue = 0f
  private var secondStartValue = 0f
  private var secondEndValue = 0f
  private var thirdStartValue = 0f
  private var thirdEndValue = 0f
  private var distance = 1f
  private var firstAnimator: ValueAnimator? = null
  private var secondAnimator: ValueAnimator? = null
  private var thirdAnimator: ValueAnimator? = null
  private var isPInit = false;
  init {
    setWillNotDraw(false)
    paint.isAntiAlias = true
    paint.style = Paint.Style.STROKE
    paint.strokeWidth = strokeWidth

    ovalPaint.isAntiAlias = true
    ovalPaint.style = Paint.Style.STROKE
    ovalPaint.strokeWidth = strokeWidth
    ovalPaint.strokeCap = Paint.Cap.ROUND


    backgroundPaint.isAntiAlias = true
    backgroundPaint.color = Color.parseColor("#ffffff")
    backgroundPaint.style = Paint.Style.STROKE
    backgroundPaint.strokeWidth = strokeWidth
  }

  private fun initResource() {
    if(!isInit) {
      radius = if (radius == 0f) height.halfToFloat() else radius
      isInit = true
      if (height.halfToFloat() < radius) {
        isDrawError = true
        return
      }
      progressPath.moveTo(strokeWidth.half(), height.halfToFloat())
      progressPath.lineTo(strokeWidth.half(), radius)
      val leftTopRect = RectF(strokeWidth.half(), strokeWidth.half(), radius + radius - strokeWidth.half(), radius + radius - strokeWidth.half())
      progressPath.arcTo(leftTopRect, 180f, 90f)
      progressPath.lineTo(width - radius, strokeWidth.half())
      val rightTopRect = RectF(width - (radius - strokeWidth.half()) * 2, strokeWidth.half(), width - strokeWidth.half(), (radius - strokeWidth.half()) * 2)
      progressPath.arcTo(rightTopRect, 270f, 90f)
      val rightBottomRect = RectF(width - 2 * radius + strokeWidth.half(), height - 2 * radius + strokeWidth.half(), width - strokeWidth.half(), height - strokeWidth.half())
      progressPath.arcTo(rightBottomRect, 0f, 90f)
      progressPath.lineTo(radius - strokeWidth.half(), height - strokeWidth.half())
      val leftBottomRect = RectF(strokeWidth.half(), height - 2 * radius + strokeWidth.half(), radius + radius - strokeWidth.half(), height - strokeWidth.half())
      progressPath.arcTo(leftBottomRect, 90f, 90f)
      progressPath.lineTo(strokeWidth.half(), height.halfToFloat())
      pathMeasure = PathMeasure()
      pathMeasure1 = PathMeasure()
      pathMeasure2 = PathMeasure()
      pathMeasure?.setPath(progressPath, false)
      pathMeasure1?.setPath(progressPath, false)
      pathMeasure2?.setPath(progressPath, false)
    }
  }

  private fun startSecondAnimator() {
    if (secondAnimator == null) {
      secondAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
        addUpdateListener { v ->
          secondStartValue = v?.animatedValue.toString().toFloat() * 1.25f
          when {
            secondStartValue in 0.25f .. 1f -> {
              secondEndValue = secondStartValue
              secondStartValue = secondEndValue - 0.25f
            }
            secondStartValue < 0.25f -> {
              secondEndValue = secondStartValue
              secondStartValue = 0f
            }
            else -> {
              startThirdAnimator()
              secondEndValue = 1f
              secondStartValue = 1f - (1.25f - secondStartValue)
            }
          }
          invalidate()
        }
        interpolator = LinearInterpolator()
        duration = 2500L
      }
      secondAnimator?.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {
        }

        override fun onAnimationEnd(p0: Animator?) {
          secondAnimator = null
          secondEndValue = 0f
          secondStartValue = 0f
        }

        override fun onAnimationCancel(p0: Animator?) {
          secondAnimator = null
          secondEndValue = 0f
          secondStartValue = 0f
        }

        override fun onAnimationStart(p0: Animator?) {
        }
      })
      secondAnimator?.start()
    }
  }

  private var ani: ValueAnimator? = null
  private var cur = 0f
  private fun startAni() {
    thirdAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
      addUpdateListener { v ->
        cur = v?.animatedValue.toString().toFloat()
        invalidate()
      }
      interpolator = LinearInterpolator()
      duration = 2500L
      repeatCount = -1
    }

    thirdAnimator?.start()
  }

  private fun startThirdAnimator() {
    if (thirdAnimator == null) {
      thirdAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
        addUpdateListener { v ->
          thirdStartValue = v?.animatedValue.toString().toFloat() * 1.25f
          when {
            thirdStartValue in 0.25f .. 1f -> {
              thirdEndValue = thirdStartValue
              thirdStartValue = thirdEndValue - 0.25f
            }
            thirdStartValue < 0.25f -> {
              thirdEndValue = thirdStartValue
              thirdStartValue = 0f
            }
            else -> {
              startSecondAnimator()
              thirdEndValue = 1f
              thirdStartValue = 1f - (1.25f - thirdStartValue)
            }
          }
          invalidate()
        }
        interpolator = LinearInterpolator()
        duration = 2500L
      }
      thirdAnimator?.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {
        }

        override fun onAnimationEnd(p0: Animator?) {
          thirdAnimator = null
          thirdStartValue = 0f
          thirdEndValue = 0f
        }

        override fun onAnimationCancel(p0: Animator?) {
          thirdAnimator = null
          thirdStartValue = 0f
          thirdEndValue = 0f
        }

        override fun onAnimationStart(p0: Animator?) {
        }
      })
      thirdAnimator?.start()
    }
  }

  private fun drawRunAnimator(canvas: Canvas?) {
    if(!isPInit){
      isPInit = true
      ovalPaint.shader = LinearGradient(0f,height/2f,width.toFloat(),height/2f, c2, null, Shader.TileMode.CLAMP)
    }
    if (!isInit) {
      initResource()
    }
    if (isInit) {
//      firstPath.reset()
//      firstPath.moveTo(strokeWidth.half(), height.halfToFloat())
//      pathMeasure?.getSegment(firstStartValue * pathMeasure!!.length, firstEndValue * pathMeasure!!.length, firstPath, true)
//      canvas?.drawPath(firstPath, ovalPaint)
      secondPath.reset()
//      secondPath.moveTo(strokeWidth.half(), height.halfToFloat())
      var x = 0f
      var y = 0f
      if(cur <= 0.5f){
        x = width * cur * 2
      }else{
        x = width - width * (cur-0.5f) * 2f
      }

      if(cur <= 0.25f){
        y = height.halfToFloat()*(0.25f -cur)*4
      }
      if(cur >0.25f && cur <= 0.75f){
        y = height * (cur -0.25f)*2
      }
      if(cur > 0.75f){
        y = height-height.halfToFloat() *(cur-0.75f)*4
      }
      secondPath.moveTo(x,y)
      Log.v("AAAAA","$x  $y")

      pathMeasure1?.getSegment(0.5f * pathMeasure1!!.length, 1 * pathMeasure1!!.length, secondPath, true)
      canvas?.drawPath(secondPath, ovalPaint)

//      thirdPath.reset()
//      thirdPath.moveTo(strokeWidth.half(), height.halfToFloat())
//      pathMeasure2?.getSegment((1-cur) * pathMeasure2!!.length, 1 * pathMeasure2!!.length, thirdPath, true)
//      canvas?.drawPath(thirdPath, ovalPaint)
    }
  }

  private fun drawGradientAnimator(canvas: Canvas?) {
    canvas?.drawPath(progressPath, backgroundPaint)
    firstPath.reset()
    firstPath.moveTo(strokeWidth.half(), height.halfToFloat())
    pathMeasure?.getSegment(0f, 0f, firstPath, true)
    canvas?.drawPath(firstPath, backgroundPaint)

    secondPath.reset()
    secondPath.moveTo(strokeWidth.half(), height.halfToFloat())
    pathMeasure1?.getSegment(0f, 0f, secondPath, true)
    canvas?.drawPath(secondPath, backgroundPaint)

    thirdPath.reset()
    thirdPath.moveTo(strokeWidth.half(), height.halfToFloat())
    pathMeasure2?.getSegment(0f, 0f, thirdPath, true)
    canvas?.drawPath(thirdPath, backgroundPaint)
    val rectF = RectF(strokeWidth.half(), strokeWidth.half(), width.toFloat() - strokeWidth.half(), height.toFloat() - strokeWidth.half())
//    if (startColor != null && endColor != null) {
      paint.shader = setGradientColor(Color.parseColor(startColor), Color.parseColor(endColor))
      //paint.shader = setGradientColor(x1,x2,y1,y2)
     // Log.v("AAAAA","$x1 $y1 $x2 $y2")

 //   }
    radius = if (radius == 0f) height.halfToFloat() else radius
    canvas?.drawRoundRect(rectF, radius, radius, paint)
  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    when (animatorType) {
      AnimatorType.Run -> drawRunAnimator(canvas)
      AnimatorType.Gradient -> drawGradientAnimator(canvas)
    }
  }

  var x1:Float = 0f
  var x2:Float = 0f
  var y1:Float = 0f
  var y2:Float = 0f
  private fun initValueAnimator() {
    if(valueAnimator == null) {
      valueAnimator = ValueAnimator.ofInt(0, 360).apply {
        addUpdateListener { animation ->
          val value = animation?.animatedValue.toString().toInt()
          startValue = if (value >= 360f) 0f else value.toFloat()
//          x1 = value/360f * width
//          x2 = (360-value)/360f * width
//          Log.v("BBBBBBB","$(value/360f)"+(value/360f*width)+" == "+((360-value)/360f*width))
//
//          y1 = value/360f*height
//          y2 = (360-value)/360f *height
          endValue = if ((value + 50) >= 360f) (value + 50 - 360f) else value.toFloat() + 50
          startColor = ColorUtil.hsbTorgb(startValue, 1f, 1f)
          endColor = ColorUtil.hsbTorgb(endValue, 1f, 1f)
          invalidate()
        }
        repeatCount = INFINITE
        duration = totalTime
      }
    }
  }


  val c1 = intArrayOf(Color.parseColor("#0ac3ff"),Color.parseColor("#ff28d3"))
  val c2 = intArrayOf(Color.parseColor("#0ac3ff"),Color.parseColor("#70a6f1"),Color.parseColor("#ff28d3"),Color.parseColor("#ff28d3"))

  val c3 = intArrayOf(Color.parseColor("#0ac3ff"),Color.parseColor("#70a6f1"),Color.parseColor("#ff28d3"),Color.parseColor("#ff28d3"))

  val c4 = intArrayOf(Color.parseColor("#0ac3ff"),Color.parseColor("#70a6f1"),Color.parseColor("#ff28d3"),Color.parseColor("#ff28d3"))

  fun startAnim(animatorType: AnimatorType) {
    //animatorType?.apply { this@GradientStrokeView.animatorType = this }
    this.animatorType = animatorType
    if (animatorType == AnimatorType.Gradient) {
      stopAnim()
      initValueAnimator()
      valueAnimator?.start()
    } else {
      stopAnim()
      startAni()
    }
  }

  private fun stopAnim() {
    valueAnimator?.cancel()
    firstAnimator?.cancel()
    secondAnimator?.cancel()
    thirdAnimator?.cancel()
    valueAnimator = null
    firstAnimator = null
    secondAnimator = null
    thirdAnimator = null
    firstStartValue = 0f
    firstEndValue = 0f
    secondStartValue = 0f
    secondEndValue = 0f
    thirdStartValue = 0f
    thirdEndValue = 0f
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    stopAnim()
  }

  private fun setGradientColor(startColor: Int, endColor: Int): LinearGradient =
//          LinearGradient(0f, 0f, width.toFloat(), height.toFloat(), c2, null, Shader.TileMode.CLAMP)
//
//  private fun setGradientColor(x1: Float, y1:Float, x2: Float, y2:Float): LinearGradient =
//          LinearGradient(x1,y1,x2,y2, c2, null, Shader.TileMode.CLAMP)

    LinearGradient(0f, 20.uiFloat(), 80.uiFloat(), height.toFloat(), startColor, endColor, Shader.TileMode.CLAMP)
}

enum class AnimatorType {
  Gradient, Run
}

