import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun EnterOTP(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
                .requiredWidth(width = 393.dp)
                .requiredHeight(height = 852.dp)
                .background(color = Color.White)
        ) {
        Box(
            modifier = Modifier
                        .requiredWidth(width = 393.dp)
                        .requiredHeight(height = 42.dp)
            ) {
            Box(
                modifier = Modifier
                                .requiredWidth(width = 393.dp)
                                .requiredHeight(height = 42.dp)
                                .background(color = Color.White))
            Box(
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 24.dp,
                                                y = 12.dp)
                                .requiredWidth(width = 54.dp)
                                .requiredHeight(height = 18.dp)
                ) {
                Box(
                    modifier = Modifier
                                        .requiredWidth(width = 54.dp)
                                        .requiredHeight(height = 18.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_513pm),
                    contentDescription = "5:13 PM",
                    modifier = Modifier
                                        .align(alignment = Alignment.TopStart)
                                        .offset(x = 4.dp,
                                                            y = 4.dp)
                                        .requiredWidth(width = 46.dp)
                                        .requiredHeight(height = 10.dp))
                }
            Image(
                painter = painterResource(id = R.drawable.alarm),
                contentDescription = "Alarm",
                colorFilter = ColorFilter.tint(AppColors.color_Gray_900),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 267.dp,
                                                y = 12.dp)
                                .requiredSize(size = 18.dp))
            Image(
                painter = painterResource(id = R.drawable.wifi),
                contentDescription = "Wifi",
                colorFilter = ColorFilter.tint(AppColors.color_Gray_900),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 309.dp,
                                                y = 12.dp)
                                .requiredSize(size = 18.dp))
            Image(
                painter = painterResource(id = R.drawable.bluetooth),
                contentDescription = "Bluetooth",
                colorFilter = ColorFilter.tint(Color(0xff222227)),
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 288.dp,
                                                y = 12.dp)
                                .requiredSize(size = 18.dp))
            Image(
                painter = painterResource(id = R.drawable.signal),
                contentDescription = "Signal",
                colorFilter = ColorFilter.tint(AppColors.color_Gray_900),
                modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 330.dp,
                                                end = 45.dp,
                                                top = 12.000000953674316.dp,
                                                bottom = 11.99999713897705.dp))
            Image(
                painter = painterResource(id = R.drawable.battery),
                contentDescription = "Battery",
                colorFilter = ColorFilter.tint(AppColors.color_Gray_900),
                modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 351.dp,
                                                end = 24.dp,
                                                top = 12.000000953674316.dp,
                                                bottom = 11.99999713897705.dp))
            }
        Icon(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "arrow_back",
            tint = Color.Black,
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 24.dp,
                                    y = 74.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 24.dp,
                                    y = 123.dp)
            ) {
            Text(
                text = "Enter OTP",
                color = AppColors.color_Primary_600,
                lineHeight = 1.25.em,
                style = AppTypes.type_Header_Header_2)
            Text(
                text = "Enter the OTP code we just sent\nyou on your registered Email/Phone number",
                color = AppColors.color_Gray_700,
                textAlign = TextAlign.Center,
                lineHeight = 1.57.em,
                style = AppTypes.type_Body_Regular_400,
                modifier = Modifier
                                .requiredWidth(width = 345.dp))
            }
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 24.dp,
                                    y = 255.dp)
                        .requiredWidth(width = 344.dp)
                        .requiredHeight(height = 70.dp)
            ) {
            Box(
                modifier = Modifier
                                .requiredWidth(width = 56.dp)
                                .requiredHeight(height = 70.dp)
                                .clip(shape = RoundedCornerShape(12.dp))
                                .background(color = AppColors.color_Gray_50)
                                .border(border = BorderStroke(1.dp, AppColors.color_Gray_100),
                                                shape = RoundedCornerShape(12.dp)))
            Box(
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 72.dp,
                                                y = 0.dp)
                                .requiredWidth(width = 56.dp)
                                .requiredHeight(height = 70.dp)
                                .clip(shape = RoundedCornerShape(12.dp))
                                .background(color = AppColors.color_Gray_50)
                                .border(border = BorderStroke(1.dp, AppColors.color_Gray_100),
                                                shape = RoundedCornerShape(12.dp)))
            Box(
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 144.dp,
                                                y = 0.dp)
                                .requiredWidth(width = 56.dp)
                                .requiredHeight(height = 70.dp)
                                .clip(shape = RoundedCornerShape(12.dp))
                                .background(color = AppColors.color_Gray_50)
                                .border(border = BorderStroke(1.dp, AppColors.color_Gray_100),
                                                shape = RoundedCornerShape(12.dp)))
            Box(
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 216.dp,
                                                y = 0.dp)
                                .requiredWidth(width = 56.dp)
                                .requiredHeight(height = 70.dp)
                                .clip(shape = RoundedCornerShape(12.dp))
                                .background(color = AppColors.color_Gray_50)
                                .border(border = BorderStroke(1.dp, AppColors.color_Gray_100),
                                                shape = RoundedCornerShape(12.dp)))
            Box(
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 288.dp,
                                                y = 0.dp)
                                .requiredWidth(width = 56.dp)
                                .requiredHeight(height = 70.dp)
                                .clip(shape = RoundedCornerShape(12.dp))
                                .background(color = AppColors.color_Gray_50)
                                .border(border = BorderStroke(1.dp, AppColors.color_Gray_100),
                                                shape = RoundedCornerShape(12.dp)))
            }
        Box(
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 24.dp,
                                    y = 377.dp)
                        .requiredWidth(width = 345.dp)
                        .requiredHeight(height = 60.dp)
            ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                                .requiredWidth(width = 345.dp)
                                .requiredHeight(height = 60.dp)
                                .clip(shape = RoundedCornerShape(14.dp))
                                .background(color = AppColors.color_Primary_500)
                                .padding(horizontal = 24.dp,
                                                vertical = 18.dp)
                ) {
                Text(
                    text = "Reset Password",
                    color = AppColors.color_Gray_White,
                    lineHeight = 1.5.em,
                    style = AppTypes.type_Body_Large_500)
                }
            Box(
                modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(x = 24.dp,
                                                y = 39.dp)
                                .requiredWidth(width = 297.dp)
                                .requiredHeight(height = 14.dp)
                                .blur(radius = 44.dp)
                                .background(color = AppColors.color_Primary_500))
            }
        Text(
            lineHeight = 2.sp,
            text = buildAnnotatedString {
    withStyle(style = SpanStyle(
        color = AppColors.color_Gray_800,
        fontSize = 14.sp)) {append("Didn’t get OTP? ")}
    withStyle(style = SpanStyle(
        color = AppColors.color_Primary_500,
        fontSize = 14.sp)) {append("Resend OTP")}},
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 48.dp,
                                    y = 453.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 137.dp,
                                    y = 790.dp)
            ) {
            Box(
                modifier = Modifier
                                .requiredWidth(width = 32.dp)
                                .requiredHeight(height = 4.dp)
                                .clip(shape = RoundedCornerShape(2.dp))
                                .background(color = AppColors.color_Primary_100))
            Box(
                modifier = Modifier
                                .requiredWidth(width = 32.dp)
                                .requiredHeight(height = 4.dp)
                                .clip(shape = RoundedCornerShape(2.dp))
                                .background(color = AppColors.color_Primary_500))
            Box(
                modifier = Modifier
                                .requiredWidth(width = 32.dp)
                                .requiredHeight(height = 4.dp)
                                .clip(shape = RoundedCornerShape(2.dp))
                                .background(color = AppColors.color_Primary_100))
            }
        }
 }

@Preview(widthDp = 393, heightDp = 852)
@Composable
private fun EnterOTPPreview() {
    EnterOTP(Modifier)
 }